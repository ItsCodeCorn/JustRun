package rungame.game.effects;

import rungame.framework.Engine;
import rungame.framework.resources.Resources;
import rungame.game.entities.Entity;
import rungame.game.states.PlayingState;

public class SpeedDownMonsterEffect extends Effect {
    public SpeedDownMonsterEffect(PlayingState playingState) {
        super(Resources.SPEED_DOWN_MONSTER_ITEM, playingState);
    }

    public void launch() {
        for (Entity entity : playingState.getEntities()) {
            if (entity == playingState.getPlayer()) {
                continue;
            }
            entity.setMoveTime(Engine.MONSTER_MOVE_TIME * 2);
        }
    }

    public void reset() {
        for (Entity entity : playingState.getEntities()) {
            if (entity == playingState.getPlayer()) {
                continue;
            }
            entity.setMoveTime(Engine.MONSTER_MOVE_TIME);
        }
    }
}