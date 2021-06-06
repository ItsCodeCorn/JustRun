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
    private int index;
    private Text restart;
    private Text menu;

    public GameOverState(StateManager stateManager) {
        super(stateManager);

        this.index = 0;

        restart = new Text("Restart");
        menu = new Text("Return to menu");

        gameOver = null;
    }

    @Override
    public void init() {
        System.out.println("[執行階段][GameOverState] init 執行中...");

        gameOver = Resources.getSprite(Resources.GAME_OVER);

        restart.setColor(255, 0, 255);
        restart.setFont(Font.MONOSPACED, Font.BOLD, 50);

        menu.setColor(255, 0, 255);
        menu.setFont(Font.MONOSPACED, Font.BOLD, 50);

        System.out.println("[執行階段][GameOverState] init 執行完成!");
    }

    @Override
    public void tick() {
        this.input();

        this.checkIndex();

        Engine.render();
    }

    @Override
    public void render(Graphics g) {
        int x = (1280 - gameOver.getWidth()) / 2;
        int y = (720 - gameOver.getHeight()) / 2;

        g.setColor(new Color(255, 255, 255));
        g.fillRect(x, y + gameOver.getHeight() - 40, gameOver.getWidth(), 140);

        gameOver.draw(g, x, y - 40);

        FontMetrics fontMetrics = g.getFontMetrics(new Font(Font.MONOSPACED, Font.BOLD, 50));
        int textX = x + (gameOver.getWidth() - fontMetrics.stringWidth("Restart")) / 2;
        int textY = y + gameOver.getHeight() + (100 - fontMetrics.getHeight()) / 2;
        restart.draw(g, textX, textY);

        textX = x + (gameOver.getWidth() - fontMetrics.stringWidth("Return to menu")) / 2;
        textY = textY + 50;
        menu.draw(g, textX, textY);
    }

    @Override
    public void input() {
        if (Input.isPressedOnce(KeyEvent.VK_W) || Input.isPressedOnce(KeyEvent.VK_UP)) {
            if (index > 0) {
                --index;
            }
        }

        if (Input.isPressedOnce(KeyEvent.VK_S) || Input.isPressedOnce(KeyEvent.VK_DOWN)) {
            if (index < 1) {
                ++index;
            }
        }

        if (Input.isPressedOnce(KeyEvent.VK_ENTER)) {
            Engine.sleep();
            stateManager.backState();
            stateManager.backState();

            switch (index) {
            case 0:
                this.stateManager.nextState(new ChooseMapState(this.stateManager));
                break;

            case 1:
                break;
            }

            Engine.wake();
        }
    }

    public void checkIndex() {
        switch (index) {
        case 0:
            this.restart.setColor(255, 0, 255);
            this.menu.setColor(0, 0, 0);
            break;

        case 1:
            this.restart.setColor(0, 0, 0);
            this.menu.setColor(255, 0, 255);
            break;
        }
    }
}
