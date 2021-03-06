package rungame.game.effects;

import rungame.framework.Engine;
import rungame.framework.utils.Counter;
import rungame.game.states.PlayingState;

public abstract class Effect {
    public static final int SPEED_UP_PLAYER_EFFECT = 0;
    public static final int SPEED_DOWN_MONSTER_EFFECT = 1;
    public static final int ELIMINATE_MONSTERS_EFFECT = 2;
    public static final int SCARE_MONSTERS_EFFECT = 3;

    public static final int DURATION = (int)(5.0d * (1000 / Engine.TICK));

    protected PlayingState playingState;
    private boolean trigger;
    private int resourceId;
    protected Counter effectCounter;

    public Effect(int resourceId, PlayingState playingState) {
        this.playingState = playingState;
        this.resourceId = resourceId;
        this.trigger = false;
        this.effectCounter = new Counter(DURATION);
    }

    public void toggle() {
        if (this.trigger) {
            this.trigger = false;
        } else {
            this.trigger = true;
        }

        effectCounter.reset();
    }

    public void trigger() {
        this.trigger = true;
        effectCounter.reset();
    }

    public void check() {
        if (!trigger) {
            return;
        }

        launch();

        if (effectCounter.count()) {
            toggle();
            reset();
        }
    }

    protected abstract void launch();
    protected abstract void reset();

    public boolean isTrigger() {
        return trigger;
    }

    public int getResourceId() {
        return this.resourceId;
    }
}