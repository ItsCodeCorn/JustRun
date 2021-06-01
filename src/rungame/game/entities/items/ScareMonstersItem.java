package rungame.game.entities.items;

import java.awt.Rectangle;

import rungame.framework.resources.Resources;
import rungame.game.effects.Effect;

public class ScareMonstersItem extends EffectItem {
    public ScareMonstersItem(int x, int y) {
        super(Resources.SCARE_MONSTERS_ITEM, 'S', new Rectangle(x, y, 25, 25), Effect.SCARE_MONSTERS_EFFECT);
    }
}
