package rungame.game.entities.items;

import java.awt.Rectangle;

import rungame.framework.resources.Resources;

public class SpeedDownMonsterItem extends Item {
    public SpeedDownMonsterItem(int x, int y) {
        super(Resources.SPEED_DOWN_MONSTER_ITEM, 'D', new Rectangle(x, y, 25, 25));
        // TODO: Speed Down Item
    }
}
