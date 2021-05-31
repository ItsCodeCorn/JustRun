package rungame.game.effects;

import rungame.framework.Engine;
import rungame.game.entities.Entity;

public class SpeedDownMonsterEffect extends Effect {
    public SpeedDownMonsterEffect() {
        super();
    }

    public void launch() {
        for (Entity entity : Engine.getPlayingState().getEntities()) {
            if (entity == Engine.getPlayer()) {
                continue;
            }
            entity.setMoveTime((int)(Engine.MONSTER_MOVE_TIME * 9 / 3 + 0.5));
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