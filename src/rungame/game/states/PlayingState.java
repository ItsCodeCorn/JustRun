package rungame.game.states;

import java.util.LinkedList;
import java.lang.Math;
import java.util.Vector;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import rungame.framework.Engine;
import rungame.framework.utils.Counter;
import rungame.game.RunGame;
import rungame.game.entities.Entity;
import rungame.game.entities.items.Item;
import rungame.game.entities.Monster;
import rungame.game.entities.Player;
import rungame.game.entities.StaticEntity;
import rungame.game.entities.Wall;
import rungame.game.factories.EntityFactory;
import rungame.game.map.Map;

public class PlayingState extends State {
    private Map map;
    private Player player;
    private LinkedList<Entity> entities;
    private LinkedList<Wall> walls;
    private LinkedList<Item> items;
    private int itemEffect;
    private Counter summonMonsterCounter;
    private Counter summonItemCounter;
    private boolean gameOver;

    public PlayingState() {
        map = Map.loadMap("Level");
        entities = new LinkedList<>();
        walls = new LinkedList<>();
        items = new LinkedList<>();
        itemEffect = 0;
        summonMonsterCounter = new Counter(Engine.SUMMON_MONSTER_TIME);
        summonItemCounter = new Counter(Engine.SUMMON_ITEM_TIME);
        gameOver = false;
    }

    public void init() {
        player = EntityFactory.createPlayer(1, 1);
        player.printMap();

        addAllWall();

        entities.addFirst(player);

        summonMonsterCounter.setFinishedCount(0);
        summonMonster();
        summonMonsterCounter.setFinishedCount(Engine.SUMMON_MONSTER_TIME);
    }

    @Override
    public void tick() {
        entities.forEach(entity -> entity.action());

        checkPickUpItem();
        checkGameOver();

        //checkItemsEffect();

        summonMonster();
        summonItem();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(50, 50, 50));
        g.fillRect(0, 0, getMapWidth() * 25, getMapHeight() * 25);

        walls.forEach(entity -> entity.draw(g));
        entities.forEach(entity -> entity.draw(g));
        items.forEach(entity -> entity.draw(g));
    }

    private void addAllWall() {
        for (int x = 0; x < map.getWidth(); ++x) {
            for (int y = 0; y < map.getHeight(); ++y) {
                if (map.getChar(x, y) == '*') {
                    walls.addFirst(EntityFactory.createWall(x, y));
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
        Engine.render();
        Engine.stop();
    }
/*
    private void displayGameOver() {
        JLabel gameOver = new JLabel(new ImageIcon("res/GameOver.png"));

        gameOver.setBounds(0, 0, 500, 250);
        gameOver.setLocation(390, 235);
        RunGame.getGamePanel().add(gameOver);
    }
*/
    public void summonMonster() {
        if (!summonMonsterCounter.count()) {
            return;
        }

        Point loc = getRandomLocation();

        Monster monster = EntityFactory.createMonster(loc.x, loc.y);
        monster.printMap();
        entities.addFirst(monster);
    }

    public void summonItem() {
        if (!summonItemCounter.count()) {
            return;
        }

        Point loc = getRandomLocation();

        Item item = EntityFactory.createRandomItem(loc.x, loc.y);
        item.printMap();
        items.addFirst(item);
    }

    public Point getRandomLocation() {
        Vector<Point> pool = new Vector<>();

        Rectangle p = player.getBounds();
        p.x /= 25;
        p.y /= 25;

        for (int x = 0; x < getMapWidth(); ++x) {
            for (int y = 0; y < getMapHeight(); ++y) {
                if (getMapChar(x, y) == ' ' && ((x < p.x - 4 || x > p.x + 4) || (y < p.y - 4 || y > p.y + 4))) {
                    pool.add(new Point(x, y));
                }
            }
        }

        return pool.get((int)Math.floor(Math.random() * pool.size()));
    }
/*
    public void checkItemsEffect() {
        if () {

        }
    }*/

    public void checkPickUpItem() {
        for (Item item : items) {
            if (item.getLocation().equals(player.getLocation())) {
                items.remove(item);
                // TODO: pick up item
                return;
            }
        }
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
    public LinkedList<Wall> getWalls() {
        return walls;
    }
}
