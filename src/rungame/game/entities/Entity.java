package rungame.game.entities;

import java.util.LinkedList;

import rungame.RunGame;

import java.awt.Rectangle;
import java.awt.Point;

public abstract class Entity extends StaticEntity {
    protected Point speed;
    protected int moveTimer;
    protected int moveTime;
    protected LinkedList<Character> hasCollision;

    public Entity(String type, char sign, Rectangle bounds) {
        super(type, sign, bounds);

        speed = new Point(0, 0);
        moveTimer = 10;
    }

    public int getMoveTimer() {
        return moveTimer;
    }
    public void countDownMoveTimer() {
        --moveTimer;
    }
    public void resetMoveTimer() {
        this.moveTimer = moveTime;
    }

    public boolean canMove() {
        return moveTimer == 0;
    }

    public abstract void action();
    public void moveDistance(int dx, int dy) {
        Point newLoc = bounds.getLocation();
        newLoc.translate(dx, dy);

        for (char sign : hasCollision) {
            if (RunGame.getGame().isCollideWith(newLoc.x / 25, newLoc.y / 25, sign)) {
                return;
            }
        }

        eraseMap();

        bounds.setLocation(newLoc);

        printMap();
    }
}
