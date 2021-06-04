package rungame.game.effects;

import rungame.framework.resources.Resources;
import rungame.game.entities.Entity;
import rungame.game.entities.Monster;
import rungame.game.states.PlayingState;

public class EliminateMonstersEffect extends Effect {
    public EliminateMonstersEffect(PlayingState playingState) {
        super(Resources.ELIMINATE_MONSTERS_ITEM, playingState);
        effectCounter.setEndCount(0);
    }

    public void launch() {
        for (Entity entity : playingState.getEntities()) {
            if (!(entity instanceof Monster)) {
                continue;
            }
            entity.eraseMap();
        }
        playingState.getEntities().removeIf(entity -> entity instanceof Monster);
    }

    public void reset() {

    }
}