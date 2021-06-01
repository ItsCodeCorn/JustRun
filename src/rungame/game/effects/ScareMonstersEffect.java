package rungame.game.effects;

import rungame.game.entities.Entity;
import rungame.game.entities.Monster;
import rungame.game.entities.Player;
import rungame.game.entities.tracking.ScareTrack;
import rungame.framework.Engine;

public class ScareMonstersEffect extends Effect {
    public ScareMonstersEffect() {
        super();
    }

    public void launch() {
        for (Entity entity : Engine.getPlayingState().getEntities()) {
            if (entity instanceof Player) {
                continue;
            }

            ((Monster)entity).setTrackStrategy(new ScareTrack());
        }
    }

    public void reset() {
        for (Entity entity : Engine.getPlayingState().getEntities()) {
            if (entity instanceof Player) {
                continue;
            }

            ((Monster)entity).resetTrackStrategy();
        }
    }
}