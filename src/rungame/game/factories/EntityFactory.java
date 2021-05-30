package rungame.game.factories;

import rungame.game.entities.items.Item;
import rungame.game.entities.items.SpeedUpPlayerItem;
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
    public static Item createRandomItem(int x, int y) {
        Item item = new SpeedUpPlayerItem(x * 25, y * 25);

        // TODO Random

        return item;
    }
}
