
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteApp extends JFrame {
    private NoteManager noteManager;
    private DefaultListModel<Note> listModel;
    private JList<Note> noteList;
    private JTextField titleField;
    private JTextArea contentArea;

    public NoteApp() {
        noteManager = new NoteManager();
        listModel = new DefaultListModel<>();

        setTitle("Note App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Панель для списка заметок
        noteList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(noteList);
        add(scrollPane, BorderLayout.WEST);

        // Панель для редактирования заметок
        JPanel notePanel = new JPanel();
        notePanel.setLayout(new BorderLayout());

        titleField = new JTextField();
        notePanel.add(titleField, BorderLayout.NORTH);

        contentArea = new JTextArea();
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        notePanel.add(contentScrollPane, BorderLayout.CENTER);

        add(notePanel, BorderLayout.CENTER);

        // Панель с кнопками управления
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JButton saveButton = new JButton("Save");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Обработчики событий
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String content = contentArea.getText();
                if (!title.isEmpty()) {
                    Note note = new Note(title, content);
                    noteManager.addNote(note);
                    listModel.addElement(note);
                    clearFields();
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = noteList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Note selectedNote = noteManager.getNotes().get(selectedIndex);
                    titleField.setText(selectedNote.getTitle());
                    contentArea.setText(selectedNote.getContent());
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = noteList.getSelectedIndex();
                if (selectedIndex != -1) {
                    noteManager.deleteNote(selectedIndex);
                    listModel.remove(selectedIndex);
                    clearFields();
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = noteList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Note note = new Note(titleField.getText(), contentArea.getText());
                    noteManager.updateNote(selectedIndex, note);
                    listModel.set(selectedIndex, note);
                    clearFields();
                }
            }
        });

        noteList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = noteList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Note selectedNote = noteManager.getNotes().get(selectedIndex);
                    titleField.setText(selectedNote.getTitle());
                    contentArea.setText(selectedNote.getContent());
                }
            }
        });
    }

    private void clearFields() {
        titleField.setText("");
        contentArea.setText("");
    }


}