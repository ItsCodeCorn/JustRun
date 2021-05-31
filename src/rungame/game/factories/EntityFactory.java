package rungame.game.factories;

import rungame.game.entities.items.*;
import rungame.game.entities.Monster;
import rungame.game.entities.Player;
import rungame.game.entities.Wall;

public class EntityFactory {
    public static Player createPlayer(int x, int y) {
        Player player = new Player(x * 25, y * 25);
        return player;
    }
    public static Monster createMonster(int x, int y) {
        Monster monster = new Monster(x * 25, y * 25);
        return monster;
    }
    public static Wall createWall(int x, int y) {
        Wall wall = new Wall(x * 25, y * 25);
        return wall;
    }
    public static EffectItem createRandomItem(int x, int y) {
        int rd = (int)(Math.random() * 3);

        EffectItem item = null;

        switch (rd) {
        case 0:
            item = new SpeedUpPlayerItem(x * 25, y * 25);
            break;

        case 1:
            item = new SpeedDownMonsterItem(x * 25, y * 25);
            break;

        case 2:
            item = new EliminateMonstersItem(x * 25, y * 25);
            break;
        }

        return item;
    }
}
