package rungame.game.entities.tracking;

import java.awt.Point;

import rungame.game.entities.Monster;

public class StupidTrack extends MonsterTrackStrategy {
    public void track(Monster monster) {
        Point playerLoc = monster.getPlayer().getLocation();
        Point monsterLoc = monster.getLocation();

        if (monsterLoc.x < playerLoc.x) {
            monster.moveDistance(25, 0);
        } else if (monsterLoc.x > playerLoc.x) {
            monster.moveDistance(-25, 0);
        }

        if (monsterLoc.y < playerLoc.y) {
            monster.moveDistance(0, 25);
        } else if (monsterLoc.y > playerLoc.y) {
            monster.moveDistance(0, -25);
        }
    }
}
