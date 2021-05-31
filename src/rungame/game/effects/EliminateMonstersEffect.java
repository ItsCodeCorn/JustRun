package rungame.game.effects;

import rungame.framework.Engine;
import rungame.game.entities.Entity;
import rungame.game.entities.Monster;

public class EliminateMonstersEffect extends Effect {
    public EliminateMonstersEffect() {
        super();
        effectCounter.setFinishedCount(0);
    }

    public void launch() {
        for (Entity entity : Engine.getPlayingState().getEntities()) {
            if (!(entity instanceof Monster)) {
                continue;
            }
            entity.eraseMap();
        }
        Engine.getPlayingState().getEntities().removeIf(entity -> entity instanceof Monster);
    }

    public void reset() {

    }
}