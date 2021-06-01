package rungame.game.entities.tracking;

import java.awt.Point;

import rungame.game.entities.Monster;

public class ScareTrack extends MonsterTrackStrategy {
    private static Point[] direction = {new Point(25, 0), new Point(-25, 0), new Point(0, 25), new Point(0, -25)};
    public void track(Monster monster) {
        Point playerLoc = monster.getPlayer().getLocation();
        Point monsterLoc = monster.getLocation();

        if (monsterLoc.x < playerLoc.x) {
            monster.moveDistance(-25, 0);
        } else if (monsterLoc.x > playerLoc.x) {
            monster.moveDistance(25, 0);
        }

        if (monsterLoc.y < playerLoc.y) {
            monster.moveDistance(0, -25);
        } else if (monsterLoc.y > playerLoc.y) {
            monster.moveDistance(0, 25);
        }

        if (monster.getLocation().equals(monsterLoc)) {
            for (int i = 0; i < 4; ++i) {
                int rd = (int)(Math.random() * (i + 1));

                Point tmp = direction[rd];
                direction[rd] = direction[i];
                direction[i] = tmp;
            }

            for (int i = 0; monster.getLocation().equals(monsterLoc) && i < 4; ++i) {
                monster.moveDistance(direction[i].x, direction[i].y);
            }
        }
    }
}
