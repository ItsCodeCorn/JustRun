package rungame.game.entities.items;

import java.awt.Rectangle;

import rungame.framework.resources.Resources;
import rungame.game.effects.Effect;

public class SpeedUpPlayerItem extends EffectItem {
    public SpeedUpPlayerItem(int x, int y) {
        super(Resources.SPEED_UP_PLAYER_ITEM, 'U', new Rectangle(x, y, 25, 25), Effect.SPEED_UP_PLAYER_EFFECT);
    }
}
