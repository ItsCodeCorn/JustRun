package rungame.game.entities.items;

import java.awt.Rectangle;

import rungame.game.entities.StaticEntity;

public abstract class Item extends StaticEntity {
    public Item(int texture, char sign, Rectangle bounds) {
        super(texture, sign, bounds);
    }
}
