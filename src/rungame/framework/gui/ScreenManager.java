package rungame.framework.gui;

import javax.swing.JFrame;

public class ScreenManager {
    private JFrame frame;
    private GameScreen gameScreen;

    public ScreenManager() {
        frame = new JFrame("Just Run!");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public void addPanel(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public void setInput() {
        Input.listenTo(gameScreen);
    }

    public void createWindow() {
        frame.setContentPane(gameScreen);
        frame.setVisible(true);
    }

    public void render() {
        gameScreen.render();
    }
}
