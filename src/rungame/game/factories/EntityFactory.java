package rungame.game.factories;

import rungame.game.entities.items.*;
import rungame.game.entities.tracking.*;
import rungame.game.states.PlayingState;
import rungame.game.entities.Monster;
import rungame.game.entities.Player;
import rungame.game.entities.Wall;

public class EntityFactory {
    public static Player createPlayer(int x, int y, PlayingState playingState) {
        Player player = new Player(x * 25, y * 25);

        player.setPlayingState(playingState);
        return player;
    }
    public static Monster createMonster(int x, int y, PlayingState playingState) {
        Monster monster = null;

        int rd = (int)(Math.random() * 3);

        switch (rd) {
        case 0:
            monster = new Monster(x * 25, y * 25, new StupidTrack());
            break;

        case 1:
            monster = new Monster(x * 25, y * 25, new RadiusTrack(5));
            break;

        case 2:
            monster = new Monster(x * 25, y * 25, new SmartTrack());
            break;
        }

        monster.setPlayingState(playingState);
        monster.setPlayer(playingState.getPlayer());
        return monster;
    }
    public static Wall createWall(int x, int y, PlayingState playingState) {
        Wall wall = new Wall(x * 25, y * 25);

        wall.setPlayingState(playingState);
        return wall;
    }
    public static EffectItem createRandomItem(int x, int y, PlayingState playingState) {
        int rd = (int)(Math.random() * 4);

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

        case 3:
            item = new ScareMonstersItem(x * 25, y * 25);
            break;
        }

        item.setPlayingState(playingState);
        return item;
    }
}
