package rungame.game.entities;

import java.awt.Rectangle;

import rungame.framework.resources.Resources;

public class Wall extends StaticEntity {
    public Wall(int x, int y) {
        super(Resources.WALL, '*', new Rectangle(x, y, 25, 25));
    }
}
