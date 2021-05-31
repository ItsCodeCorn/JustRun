package rungame.game.entities.items;

import java.awt.Rectangle;

import rungame.framework.resources.Resources;
import rungame.game.effects.Effect;

public class EliminateMonstersItem extends EffectItem {
    public EliminateMonstersItem(int x, int y) {
        super(Resources.ELIMINATE_MONSTERS_ITEM, 'E', new Rectangle(x, y, 25, 25), Effect.ELIMINATE_MONSTERS_EFFECT);
    }
}
