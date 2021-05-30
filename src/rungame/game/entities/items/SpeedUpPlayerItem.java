package rungame.game.entities.items;

import java.awt.Rectangle;

import rungame.framework.resources.Resources;

public class SpeedUpPlayerItem extends Item {
    public SpeedUpPlayerItem(int x, int y) {
        super(Resources.SPEED_UP_PLAYER_ITEM, 'U', new Rectangle(x, y, 25, 25));
    }
}
