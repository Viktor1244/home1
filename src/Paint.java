import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Paint extends JFrame {
    private BufferedImage canvas;
    private Graphics2D g2;
    private int x0, y0, x1, y1;

    public Paint() {
        setTitle("Simple Paint");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        g2 = canvas.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 800, 600);
        g2.setColor(Color.BLACK);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(canvas, 0, 0, null);
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x0 = e.getX();
                y0 = e.getY();
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
                g2.drawLine(x0, y0, x1, y1);
                x0 = x1;
                y0 = y1;
                repaint();
            }
        });

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g2.setColor(Color.WHITE);
                g2.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                g2.setColor(Color.BLACK);
                repaint();
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(clearButton);

        add(panel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Paint());
    }
}








