package rungame.game.effects;

import rungame.game.entities.Entity;
import rungame.game.entities.Monster;
import rungame.game.entities.Player;
import rungame.game.entities.tracking.ScareTrack;
import rungame.game.states.PlayingState;
import rungame.framework.resources.Resources;

public class ScareMonstersEffect extends Effect {
    public ScareMonstersEffect(PlayingState playingState) {
        super(Resources.SCARE_MONSTERS_ITEM, playingState);
    }

    public void launch() {
        playingState.getPlayer().setInvulnerable(true);
        playingState.getPlayer().getHasCollision().remove((Character)'M');

        for (Entity entity : playingState.getEntities()) {
            if (entity instanceof Player) {
                continue;
            }

            ((Monster)entity).setTrackStrategy(new ScareTrack());
        }
    }

    public void reset() {
        playingState.getPlayer().setInvulnerable(false);
        playingState.getPlayer().getHasCollision().add('M');

        for (Entity entity : playingState.getEntities()) {
            if (entity instanceof Player) {
                continue;
            }

            ((Monster)entity).resetTrackStrategy();
        }
    }
}