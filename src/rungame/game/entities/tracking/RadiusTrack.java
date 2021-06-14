package rungame.game.entities.tracking;

import java.awt.Point;

import rungame.game.entities.Monster;

public class RadiusTrack implements MonsterTrackStrategy {
    private int radius;

    public RadiusTrack(int radius) {
        this.radius = radius;
    }

    @Override
    public void track(Monster monster) {
        Point monsterLoc = monster.getLocation();
        Point playerLoc = monster.getPlayer().getLocation();

        playerLoc.x /= 25;
        playerLoc.y /= 25;
        monsterLoc.x /= 25;
        monsterLoc.y /= 25;

        double dx = Math.abs(monsterLoc.x - playerLoc.x), dy = Math.abs(monsterLoc.y - playerLoc.y);
        if (Math.round(Math.sqrt(dx * dx + dy * dy)) > radius) {
            return;
        }

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
