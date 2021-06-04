package rungame.game.effects;

import rungame.framework.Engine;
import rungame.framework.resources.Resources;
import rungame.game.states.PlayingState;

public class SpeedUpPlayerEffect extends Effect {
    public SpeedUpPlayerEffect(PlayingState playingState) {
        super(Resources.SPEED_UP_PLAYER_ITEM, playingState);
    }

    public void launch() {
        playingState.getPlayer().setMoveTime(Engine.PLAYER_MOVE_TIME * 2 / 3);
    }

    public void reset() {
        playingState.getPlayer().setMoveTime(Engine.PLAYER_MOVE_TIME);
    }
}