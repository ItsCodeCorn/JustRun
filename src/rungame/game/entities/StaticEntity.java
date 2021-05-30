package rungame.game.entities;

import java.awt.Rectangle;

import rungame.framework.Engine;
import rungame.framework.resources.Resources;
import rungame.framework.resources.Sprite;
import rungame.game.states.PlayingState;

import java.awt.Point;
import java.awt.Graphics;

public abstract class StaticEntity {
    protected Sprite sprite;
    protected Rectangle bounds;
    protected char sign;

    public StaticEntity(int texture, char sign, Rectangle bounds) {
        sprite = Resources.getSprite(texture);

        this.sign = sign;
        this.bounds = bounds;
    }

    public void printMap() {
        PlayingState game = Engine.getPlayingState();
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
        PlayingState game = Engine.getPlayingState();
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
