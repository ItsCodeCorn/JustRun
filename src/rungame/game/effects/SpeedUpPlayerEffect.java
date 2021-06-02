package rungame.game.effects;

import rungame.framework.Engine;
import rungame.framework.resources.Resources;

public class SpeedUpPlayerEffect extends Effect {
    public SpeedUpPlayerEffect() {
        super(Resources.SPEED_UP_PLAYER_ITEM);
    }

    public void launch() {
        Engine.getPlayingState().getPlayer().setMoveTime(Engine.PLAYER_MOVE_TIME * 2 / 3);
    }

    public void reset() {
        Engine.getPlayingState().getPlayer().setMoveTime(Engine.PLAYER_MOVE_TIME);
    }
}