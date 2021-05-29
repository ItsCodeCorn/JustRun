package rungame.game.entities;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import rungame.framework.Engine;
import rungame.framework.resources.Resources;
import rungame.game.utils.Counter;

public class Monster extends Entity {
    private Player player;

    public Monster(int x, int y) {
        super(Resources.MONSTER, 'M', new Rectangle(x, y, 25, 25));

        moveTimeCounter = new Counter(Engine.MONSTER_MOVE_TIME);

        player = Engine.getPlayer();
        hasCollision = new LinkedList<>();

        hasCollision.addFirst('*');
        hasCollision.addFirst('M');
    }

    @Override
    public void action() {
        if (!moveTimeCounter.count()) {
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
            Engine.getPlayingState().setGameOver(true);
        }
    }

}
