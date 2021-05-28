package rungame.game.states;

import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import rungame.RunGame;
import rungame.game.Map;
import rungame.game.entities.Entity;
import rungame.game.entities.Monster;
import rungame.game.entities.Player;
import rungame.game.entities.StaticEntity;
import rungame.game.factories.EntityFactory;

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

        entities.forEach(entity -> addDrawable(entity));
        staticEntities.forEach(entity -> addDrawable(entity));
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

        removeDrawable(player);
        RunGame.getGamePanel().render();
        RunGame.getTimer().stop();

        displayGameOver();
    }

    private void displayGameOver() {
        JLabel gameOver = new JLabel(new ImageIcon("res/GameOver.png"));

        gameOver.setBounds(0, 0, 500, 250);
        gameOver.setLocation(390, 235);
        RunGame.getGamePanel().add(gameOver);
    }

    public void summonMonster() {
        if (summonMonsterTimer != 0) {
            --summonMonsterTimer;
            return;
        }

        Monster monster = EntityFactory.createMonster(18, 18);
        monster.printMap();
        entities.addFirst(monster);
        addDrawable(monster);

        summonMonsterTimer = RunGame.SUMMON_MONSTER_TIME;
    }

    public void addDrawable(StaticEntity entity) {
        RunGame.getGamePanel().addDrawable(entity);
    }
    public void removeDrawable(StaticEntity entity) {
        RunGame.getGamePanel().removeDrawable(entity);
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
