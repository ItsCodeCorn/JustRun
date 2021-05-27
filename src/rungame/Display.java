package rungame;

import javax.swing.JFrame;

public class Display extends JFrame {
    public Display() {
        setTitle("Run Game");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setVisible(true);
    }
}
