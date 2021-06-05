package rungame.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import rungame.framework.Engine;
import rungame.framework.gui.Input;
import rungame.framework.resources.Resources;
import rungame.framework.resources.Sprite;
import rungame.framework.resources.Text;

public class GameOverState extends State {
    private Sprite gameOver;
    private Text restart;

    public GameOverState(StateManager stateManager) {
        super(stateManager);

        restart = new Text("Restart?");

        gameOver = null;
    }

    @Override
    public void init() {
        System.out.println("[執行階段][GameOverState] init 執行中...");

        gameOver = Resources.getSprite(Resources.GAME_OVER);

        restart.setColor(255, 0, 255);
        restart.setFont(Font.MONOSPACED, Font.BOLD, 50);

        System.out.println("[執行階段][GameOverState] init 執行完成!");
    }

    @Override
    public void tick() {
        this.input();

        Engine.render();
    }

    @Override
    public void render(Graphics g) {
        int x = (1280 - gameOver.getWidth()) / 2;
        int y = (720 - gameOver.getHeight()) / 2;

        g.setColor(new Color(255, 255, 255));
        g.fillRect(x, y + gameOver.getHeight() - 40, gameOver.getWidth(), 100);

        gameOver.draw(g, x, y - 40);

        FontMetrics fontMetrics = g.getFontMetrics(new Font(Font.MONOSPACED, Font.BOLD, 50));

        restart.draw(g, x + (gameOver.getWidth() - fontMetrics.stringWidth("Restart?")) / 2, y + gameOver.getHeight() + (100 - fontMetrics.getHeight()) / 2);
    }

    @Override
    public void input() {
        if (Input.isPressed(KeyEvent.VK_ENTER)) {
            stateManager.backState();
            stateManager.nextState(new PlayingState(stateManager));
        }
    }
}
