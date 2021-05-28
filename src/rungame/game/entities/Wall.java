package rungame.game.entities;

import java.awt.Rectangle;

public class Wall extends StaticEntity {
    public Wall(int x, int y) {
        super("Wall", '*', new Rectangle(x, y, 25, 25));
    }
}
