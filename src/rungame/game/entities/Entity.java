package rungame.game.entities;

import java.util.LinkedList;

import rungame.framework.Engine;
import rungame.game.RunGame;
import rungame.game.utils.Counter;

import java.awt.Rectangle;
import java.awt.Point;

public abstract class Entity extends StaticEntity {
    protected Point speed;
    protected Counter moveTimeCounter;
    protected LinkedList<Character> hasCollision;

    public Entity(int texture, char sign, Rectangle bounds) {
        super(texture, sign, bounds);

        speed = new Point(0, 0);
    }

    public abstract void action();
    public void moveDistance(int dx, int dy) {
        Point newLoc = bounds.getLocation();
        newLoc.translate(dx, dy);

        for (char sign : hasCollision) {
            if (Engine.getPlayingState().isCollideWith(newLoc.x / 25, newLoc.y / 25, sign)) {
                return;
            }
        }

        eraseMap();

        bounds.setLocation(newLoc);

        printMap();
    }
}
