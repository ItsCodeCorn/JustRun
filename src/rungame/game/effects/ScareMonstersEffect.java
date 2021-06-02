package rungame.game.effects;

import rungame.game.entities.Entity;
import rungame.game.entities.Monster;
import rungame.game.entities.Player;
import rungame.game.entities.tracking.ScareTrack;
import rungame.framework.Engine;
import rungame.framework.resources.Resources;

public class ScareMonstersEffect extends Effect {
    public ScareMonstersEffect() {
        super(Resources.SCARE_MONSTERS_ITEM);
    }

    public void launch() {
        Engine.getPlayer().setInvulnerable(true);
        Engine.getPlayer().getHasCollision().remove((Character)'M');

        for (Entity entity : Engine.getPlayingState().getEntities()) {
            if (entity instanceof Player) {
                continue;
            }

            ((Monster)entity).setTrackStrategy(new ScareTrack());
        }
    }

    public void reset() {
        Engine.getPlayer().setInvulnerable(false);
        Engine.getPlayer().getHasCollision().add('M');

        for (Entity entity : Engine.getPlayingState().getEntities()) {
            if (entity instanceof Player) {
                continue;
            }

            ((Monster)entity).resetTrackStrategy();
        }
    }
}