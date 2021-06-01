package rungame.game.effects;

import rungame.framework.Engine;

public class SpeedUpPlayerEffect extends Effect {
    public SpeedUpPlayerEffect() {
        super();
    }

    public void launch() {
        Engine.getPlayingState().getPlayer().setMoveTime(Engine.PLAYER_MOVE_TIME * 7 / 10);
    }

    public void reset() {
        Engine.getPlayingState().getPlayer().setMoveTime(Engine.PLAYER_MOVE_TIME);
    }
}