package rungame.game.states;

import java.awt.Graphics;

public abstract class State {
    protected StateManager stateManager;

    public State(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public abstract void init();
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void input();
}
