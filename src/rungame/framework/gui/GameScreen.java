package rungame.framework.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import rungame.game.states.PlayingState;

public class GameScreen extends JPanel {
    private PlayingState playingState;
    private long startTime;
    private long previousTime;
    private long tick = 0;
    private final Font STRING_FONT = new Font("SansSerif", Font.BOLD, 20);

    public GameScreen(PlayingState playingState) {
        this.playingState = playingState;

        setFocusable(true);
        requestFocus();
        startTime = System.currentTimeMillis();
        previousTime = System.currentTimeMillis();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        playingState.render(g);

        tick = System.currentTimeMillis() - previousTime;
        previousTime = System.currentTimeMillis();
        g.setColor(new Color(200, 0, 20));
        g.setFont(STRING_FONT);
        g.drawString("TICK: " + Long.toString(tick), 1100, 20);
        g.drawString("Times: " + Double.toString((System.currentTimeMillis() - startTime) / 1000.0d), 1100, 50);
    }

    public void render() {
        repaint();
    }

    public PlayingState getPlayingState() {
        return playingState;
    }
}
