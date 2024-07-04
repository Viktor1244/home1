import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MusicPlayerGUI extends JFrame {
    private ArrayList<String> playlist;
    private int currentSongIndex;
    private JLabel songLabel;

    public MusicPlayerGUI() {
        playlist = new ArrayList<>();
        currentSongIndex = 0;

        setTitle("Music Player");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        songLabel = new JLabel("No song playing", SwingConstants.CENTER);
        add(songLabel, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 4));

        JButton addButton = new JButton("Add");
        JButton playButton = new JButton("Play");
        JButton nextButton = new JButton("Next");
        JButton previousButton = new JButton("Previous");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSong();
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                play();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previous();
            }
        });

        panel.add(addButton);
        panel.add(playButton);
        panel.add(nextButton);
        panel.add(previousButton);

        add(panel, BorderLayout.SOUTH);
    }

    private void addSong() {
        String song = JOptionPane.showInputDialog(this, "Enter song name:");
        if (song != null && !song.isEmpty()) {
            playlist.add(song);
            JOptionPane.showMessageDialog(this, "Song added: " + song);
        }
    }

    private void play() {
        if (playlist.size() == 0) {
            songLabel.setText("Playlist is empty.");
        } else {
            songLabel.setText("Playing: " + playlist.get(currentSongIndex));
        }
    }

    private void next() {
        if (currentSongIndex < playlist.size() - 1) {
            currentSongIndex++;
            play();
        } else {
            songLabel.setText("End of playlist.");
        }
    }

    private void previous() {
        if (currentSongIndex > 0) {
            currentSongIndex--;
            play();
        } else {
            songLabel.setText("Start of playlist.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MusicPlayerGUI player = new MusicPlayerGUI();
                player.setVisible(true);
            }
        });
    }
}

