package rungame.game.states;

import java.awt.Graphics;

public abstract class State {
    protected StateManager stateManager;

    public State(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    protected abstract void init();
    protected abstract void tick();
    protected abstract void render(Graphics g);
    protected abstract void input();
}
