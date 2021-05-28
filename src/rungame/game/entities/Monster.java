package rungame.game.entities;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import rungame.RunGame;

public class Monster extends Entity {
    private Player player;

    public Monster(int x, int y) {
        super("Monster", 'M', new Rectangle(x, y, 25, 25));

        moveTime = RunGame.MONSTER_MOVE_TIME;

        player = RunGame.getGame().getPlayer();
        hasCollision = new LinkedList<>();

        hasCollision.addFirst('*');
        hasCollision.addFirst('M');
    }

    @Override
    public void action() {
        if (!canMove()) {
            countDownMoveTimer();
            return;
        }

        Point playerLoc = player.getLocation();
        Point monsterLoc = getLocation();

        if (monsterLoc.x < playerLoc.x) {
            moveDistance(25, 0);
        } else if (monsterLoc.x > playerLoc.x) {
            moveDistance(-25, 0);
        }

        if (monsterLoc.y < playerLoc.y) {
            moveDistance(0, 25);
        } else if (monsterLoc.y > playerLoc.y) {
            moveDistance(0, -25);
        }

        if (player.getLocation().equals(getLocation())) {
            RunGame.getGame().setGameOver(true);
        }

        resetMoveTimer();
    }

}
