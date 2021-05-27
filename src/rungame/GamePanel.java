package rungame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private Game game;
    private long startTime;
    private long previousTime;
    private long currentTime;

    public GamePanel(Game game) {
        this.game = game;

        setFocusable(true);
        requestFocus();
        startTime = System.currentTimeMillis();
        previousTime = System.currentTimeMillis();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(50, 50, 50));
        g.fillRect(0, 0, game.getMapWidth() * 25, game.getMapHeight() * 25);

        game.getEntities().forEach(entity -> entity.draw(g));
        game.getStaticEntities().forEach(entity -> entity.draw(g));

        currentTime = System.currentTimeMillis();

        g.setColor(new Color(200, 0, 20));
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.drawString("TICK: " + Long.toString(currentTime - previousTime), 1100, 20);
        g.drawString("Times: " + Double.toString((currentTime - startTime) / 1000.0d), 1100, 50);
        previousTime = currentTime;

    }

    public void render() {
        repaint();
    }

    public Game getGame() {
        return game;
    }
}
