package rungame.game.states;

import java.util.EmptyStackException;
import java.util.Stack;

import java.awt.Graphics;

public class StateManager {
    private Stack<State> states;

    public StateManager() {
        this.states = new Stack<>();
    }

    public void nextState(State state) {
        this.states.add(state);
        this.init();
    }

    public void backState() {
        this.states.pop();
    }

    public void clearState() {
        this.states.clear();
    }

    public void init() {
        try {
            this.states.peek().init();
        } catch (EmptyStackException e) {
            System.out.println("[錯誤][StateManager] 當前State為空.");
            System.exit(0);
        }
    }

    public void tick() {
        try {
            this.states.peek().tick();
        } catch (EmptyStackException e) {
            System.out.println("[錯誤][StateManager] 當前State為空.");
            System.exit(0);
        }
    }

    public void render(Graphics g) {
        try {
            this.states.peek().render(g);
        } catch (EmptyStackException e) {
            System.out.println("[錯誤][StateManager] 當前State為空.");
            System.exit(0);
        }
    }
}
