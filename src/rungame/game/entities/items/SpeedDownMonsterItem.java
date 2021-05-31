package rungame.game.entities.items;

import java.awt.Rectangle;

import rungame.framework.resources.Resources;
import rungame.game.effects.Effect;

public class SpeedDownMonsterItem extends EffectItem {
    public SpeedDownMonsterItem(int x, int y) {
        super(Resources.SPEED_DOWN_MONSTER_ITEM, 'D', new Rectangle(x, y, 25, 25), Effect.SPEED_DOWN_MONSTER_EFFECT);
    }
}
