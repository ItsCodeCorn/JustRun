package rungame;

import java.util.LinkedList;

public class Game {
    private Map map;
    private Player player;
    private LinkedList<Entity> entities;
    private LinkedList<StaticEntity> staticEntities;
    private int summonMonsterTimer;
    private boolean gameOver;

    public Game() {
        map = Map.loadMap("Level");
        entities = new LinkedList<>();
        staticEntities = new LinkedList<>();
        summonMonsterTimer = 0;
        gameOver = false;
    }

    public void init() {
        player = EntityFactory.createPlayer(1, 1);
        player.printMap();

        addAllWall();

        entities.addFirst(player);
    }

    private void addAllWall() {
        for (int x = 0; x < map.getWidth(); ++x) {
            for (int y = 0; y < map.getHeight(); ++y) {
                if (map.getChar(x, y) == '*') {
                    staticEntities.addFirst(EntityFactory.createWall(x, y));
                }
            }
        }
    }

    public boolean isCollideWith(int x, int y, char sign) {
        if (map.getChar(x, y) == sign) {
            return true;
        }

        return false;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    public void checkGameOver() {
        if (!gameOver) {
            return;
        }

        entities.remove(player);
        RunGame.getGamePanel().render();
        RunGame.getTimer().stop();
    }

    public void summonMonster() {
        if (summonMonsterTimer != 0) {
            --summonMonsterTimer;
            return;
        }

        Monster monster = EntityFactory.createMonster(18, 18);
        monster.printMap();
        entities.addFirst(monster);

        summonMonsterTimer = RunGame.SUMMON_MONSTER_TIME;
    }

    public int getMapWidth() {
        return map.getWidth();
    }
    public int getMapHeight() {
        return map.getHeight();
    }

    public char getMapChar(int x, int y) {
        return map.getChar(x, y);
    }
    public void setMapChar(int x, int y, char c) {
        map.setChar(x, y, c);
    }

    public Player getPlayer() {
        return player;
    }
    public LinkedList<Entity> getEntities() {
        return entities;
    }
    public LinkedList<StaticEntity> getStaticEntities() {
        return staticEntities;
    }
}
