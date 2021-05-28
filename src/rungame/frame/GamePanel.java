package rungame.frame;

import java.util.LinkedList;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import rungame.game.entities.StaticEntity;
import rungame.game.states.Game;

public class GamePanel extends JPanel {
    private Game game;
    private long startTime;
    private long previousTime;
    private long currentTime;

    private LinkedList<StaticEntity> drawableEntities;
    private LinkedList<String> drawableStrings;

    public GamePanel(Game game) {
        this.game = game;
        drawableEntities = new LinkedList<StaticEntity>();
        drawableStrings = new LinkedList<String>();

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

        drawableEntities.forEach(entity -> entity.draw(g));

        currentTime = System.currentTimeMillis();

        g.setColor(new Color(200, 0, 20));
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.drawString("TICK: " + Long.toString(currentTime - previousTime), 1100, 20);
        g.drawString("Times: " + Double.toString((currentTime - startTime) / 1000.0d), 1100, 50);
        previousTime = currentTime;

    }

    public void addDrawable(StaticEntity entity) {
        drawableEntities.addFirst(entity);
    }
    public void removeDrawable(StaticEntity entity) {
        drawableEntities.remove(entity);
    }

    public void render() {
        repaint();
    }

    public Game getGame() {
        return game;
    }
}
