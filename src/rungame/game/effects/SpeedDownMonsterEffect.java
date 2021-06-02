package rungame.game.effects;

import rungame.framework.Engine;
import rungame.framework.resources.Resources;
import rungame.game.entities.Entity;

public class SpeedDownMonsterEffect extends Effect {
    public SpeedDownMonsterEffect() {
        super(Resources.SPEED_DOWN_MONSTER_ITEM);
    }

    public void launch() {
        for (Entity entity : Engine.getPlayingState().getEntities()) {
            if (entity == Engine.getPlayer()) {
                continue;
            }
            entity.setMoveTime(Engine.MONSTER_MOVE_TIME * 2);
        }
    }

    public void reset() {
        for (Entity entity : Engine.getPlayingState().getEntities()) {
            if (entity == Engine.getPlayer()) {
                continue;
            }
            entity.setMoveTime(Engine.MONSTER_MOVE_TIME);
        }
    }
}