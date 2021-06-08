package rungame.framework.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import rungame.framework.Engine;

public class GameScreen extends JPanel {
    private long previousTime;
    private long tick = 0;
    private final Font STRING_FONT = new Font("SansSerif", Font.BOLD, 20);

    public GameScreen() {
        setFocusable(true);
        requestFocus();
        previousTime = System.currentTimeMillis();
    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);

        Engine.getStateManager().render(g);

        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 665, 90, 55);

        tick = System.currentTimeMillis() - previousTime;
        previousTime = System.currentTimeMillis();
        g.setColor(new Color(200, 0, 255));
        g.setFont(STRING_FONT);
        g.drawString("TICK: " + Long.toString(tick), 0, 680);
    }

    public void render() {
        repaint();
    }
}
