package rungame.game.entities;

import java.util.LinkedList;

import rungame.framework.Engine;
import rungame.framework.utils.Counter;

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

    public void setMoveTime(long duration) {
        moveTimeCounter.setDuration(duration);
    }

    public boolean canMove(int x, int y) {
        for (char sign : hasCollision) {
            if (Engine.getPlayingState().isCollideWith(x, y, sign)) {
                return false;
            }
        }

        return true;
    }

    public abstract void action();
    public void moveDistance(int dx, int dy) {
        Point newLoc = bounds.getLocation();

        if (dx != 0 && dy != 0) {
            if (!canMove((newLoc.x + dx) / 25, (newLoc.y + dy) / 25)) {
                if (canMove((newLoc.x + dx) / 25, newLoc.y / 25)) {
                    newLoc.translate(dx, 0);
                } else if (canMove(newLoc.x / 25, (newLoc.y + dy) / 25)) {
                    newLoc.translate(0, dy);
                } else {
                    return;
                }
            } else {
                if (!canMove((newLoc.x + dx) / 25, newLoc.y / 25) && !canMove(newLoc.x / 25, (newLoc.y + dy) / 25)) {
                    return;
                }

                newLoc.translate(dx, dy);
            }
        } else {
            if (!canMove((newLoc.x + dx) / 25, (newLoc.y + dy) / 25)) {
                return;
            }

            newLoc.translate(dx, dy);
        }

        eraseMap();

        bounds.setLocation(newLoc);

        printMap();
    }

    public void moveTo(int x, int y) {
        if (!canMove(x / 25, y / 25)) {
            return;
        }

        eraseMap();

        bounds.setLocation(x, y);

        printMap();
    }
}
