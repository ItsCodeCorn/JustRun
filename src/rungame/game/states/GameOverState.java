package rungame.game.states;

import java.awt.Graphics;

import rungame.framework.Engine;
import rungame.framework.resources.Resources;
import rungame.framework.resources.Sprite;

public class GameOverState extends State {
    private Sprite gameOver;

    public GameOverState(StateManager stateManager) {
        super(stateManager);

        gameOver = null;
    }

    @Override
    public void init() {
        System.out.println("[執行階段][GameOverState] init 執行中...");

        gameOver = Resources.getSprite(Resources.GAME_OVER);

        System.out.println("[執行階段][GameOverState] init 執行完成!");
    }

    @Override
    public void tick() {
        this.input();

        Engine.render();
    }

    @Override
    public void render(Graphics g) {
        gameOver.draw(g, (1280 - gameOver.getWidth()) / 2, (720 - gameOver.getHeight()) / 2);
    }

    @Override
    public void input() {
        
    }
}
