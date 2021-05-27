package rungame;

import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Graphics;

abstract class StaticEntity {
    protected Sprite sprite;
    protected Rectangle bounds;
    protected char sign;

    public StaticEntity(String type, char sign, Rectangle bounds) {
        sprite = SpriteFactory.getSprite(type);

        this.sign = sign;
        this.bounds = bounds;
    }

    public void printMap() {
        Game game = RunGame.getGame();
        Rectangle rec = bounds.getBounds();
        rec.x /= 25;
        rec.y /= 25;
        rec.width /= 25;
        rec.height /= 25;

        for (int i = 0; i < rec.width; ++i) {
            for (int j = 0; j < rec.height ; ++j) {
                game.setMapChar(rec.x + i, rec.y + j, sign);
            }
        }
    }
    public void eraseMap() {
        Game game = RunGame.getGame();
        Rectangle rec = bounds.getBounds();
        rec.x /= 25;
        rec.y /= 25;
        rec.width /= 25;
        rec.height /= 25;

        for (int i = 0; i < rec.width; ++i) {
            for (int j = 0; j < rec.height ; ++j) {
                game.setMapChar(rec.x + i, rec.y + j, ' ');
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(bounds);
    }
    public Point getLocation() {
        return new Point(bounds.x, bounds.y);
    }

    public void draw(Graphics g) {
        sprite.draw(g, bounds.x, bounds.y);
    }
}
