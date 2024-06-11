import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    private JLabel textField = new JLabel("0");
    private int num1 = 0;
    private int num2 = 0;
    private String operator = "";

    public Calculator() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 4, 10, 10));

        // Number buttons
        addButton("7");
        addButton("8");
        addButton("9");
        addButton("/");
        addButton("4");
        addButton("5");
        addButton("6");
        addButton("*");
        addButton("1");
        addButton("2");
        addButton("3");
        addButton("-");
        addButton("0");
        addButton("=");
        addButton("+");
        addButton("Delete");

        add(textField);
        textField.setHorizontalAlignment(JLabel.RIGHT);
        textField.setFont(new Font("Arial", Font.BOLD, 24));

        setVisible(true);
    }

    private void addButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(this);
        add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (Character.isDigit(command.charAt(0))) {
            if (textField.getText().equals("0")) {
                textField.setText(command);
            } else {
                textField.setText(textField.getText() + command);
            }
        } else {
            switch (command) {
                case "+":
                case "-":
                case "*":
                case "/":
                    num1 = Integer.parseInt(textField.getText());
                    operator = command;
                    textField.setText("0");
                    break;
                case "=":
                    num2 = Integer.parseInt(textField.getText());
                    int result = 0;
                    switch (operator) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            result = num1 / num2;
                            break;
                    }
                    textField.setText(String.valueOf(result));
                    num1 = 0;
                    num2 = 0;
                    operator = "";
                    break;
                case "Delete":
                    textField.setText("0");
                    num1 = 0;
                    num2 = 0;
                    operator = "";
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
