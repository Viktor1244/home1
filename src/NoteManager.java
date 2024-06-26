import java.util.ArrayList;

public class NoteManager {
    private ArrayList<Note> notes;

    public NoteManager() {
        notes = new ArrayList<>();
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void updateNote(int index, Note note) {
        notes.set(index, note);
    }

    public void deleteNote(int index) {
        notes.remove(index);
    }
}