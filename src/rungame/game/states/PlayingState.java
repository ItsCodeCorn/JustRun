package rungame.game.states;

import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Math;
import java.util.Vector;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

import rungame.framework.Engine;
import rungame.framework.resources.Resources;
import rungame.framework.utils.Counter;
import rungame.game.effects.*;
import rungame.game.entities.Entity;
import rungame.game.entities.items.EffectItem;
import rungame.game.entities.Monster;
import rungame.game.entities.Player;
import rungame.game.entities.Wall;
import rungame.game.factories.EntityFactory;
import rungame.game.map.Map;

public class PlayingState extends State {
    private Map map;
    private Player player;
    private LinkedList<Entity> entities;
    private LinkedList<Wall> walls;
    private LinkedList<EffectItem> items;
    private ArrayList<Effect> effects;
    private Counter summonMonsterCounter;
    private Counter summonItemCounter;
    private boolean gameOver;

    public PlayingState() {
        map = null;
        entities = new LinkedList<>();
        walls = new LinkedList<>();
        items = new LinkedList<>();
        effects = new ArrayList<>();

        summonMonsterCounter = new Counter(Engine.SUMMON_MONSTER_TIME);
        summonItemCounter = new Counter(Engine.SUMMON_ITEM_TIME);
        gameOver = false;
    }

    public void init() {
        System.out.println("[執行階段][PlayingState] init 執行中...");

        map = Map.loadMap("Level");
        Point playerLoc = map.getPlayerLocation();

        player = EntityFactory.createPlayer(playerLoc.x, playerLoc.y);
        player.printMap();
        entities.addFirst(player);

        addAllWall();

        effects.add(new SpeedUpPlayerEffect());
        effects.add(new SpeedDownMonsterEffect());
        effects.add(new EliminateMonstersEffect());
        effects.add(new ScareMonstersEffect());

        summonMonsterCounter.setEndCount(0);
        summonMonster();
        summonMonsterCounter.setEndCount(Engine.SUMMON_MONSTER_TIME);
        System.out.println("[執行階段][PlayingState] init 執行完成!");
    }

    @Override
    public void tick() {
        entities.forEach(entity -> entity.action());

        checkMonstersCollision();
        checkGameOver();

        checkPickUpItems();
        checkItemsEffect();
        printAllItems();

        summonMonster();
        summonItem();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, 2000, 2000);

        g.setColor(new Color(50, 50, 50));
        g.fillRect(0, 0, getMapWidth() * 25, getMapHeight() * 25);

        items.forEach(entity -> entity.draw(g));
        walls.forEach(entity -> entity.draw(g));
        entities.forEach(entity -> entity.draw(g));

        int effectsCount = 0;
        for (Effect effect : effects) {
            if (effect.isTrigger()) {
                Resources.getSprite(effect.getResourceId()).draw(g, 1100, 150 + (25 * effectsCount));
                ++effectsCount;
            }
        }
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

    public void printAllItems() {
        items.forEach(item -> item.printMap());
    }

    public void checkMonstersCollision() {
        for (Entity entity : entities) {
            if (entity instanceof Player) {
                continue;
            }

            if (entity.getLocation().equals(player.getLocation())) {
                if (player.isInvulnerable()) {
                    entities.remove(entity);
                } else {
                    setGameOver(true);
                }
                return;
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

        if (loc == null) {
            return;
        }

        Monster monster = EntityFactory.createMonster(loc.x, loc.y);
        monster.printMap();
        entities.addFirst(monster);
    }

    public void summonItem() {
        if (!summonItemCounter.count()) {
            return;
        }

        Point loc = getRandomLocation();

        if (loc == null) {
            return;
        }

        EffectItem item = EntityFactory.createRandomItem(loc.x, loc.y);
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

        if (pool.size() == 0) {
            return null;
        }

        return pool.get((int)Math.floor(Math.random() * pool.size()));
    }

    public void checkPickUpItems() {
        for (EffectItem item : items) {
            if (item.getLocation().equals(player.getLocation())) {
                items.remove(item);

                try {
                    effects.get(item.getEffectId()).trigger();
                } catch (RuntimeException e) {
                    System.out.println("[錯誤][PlayingState] 啟動效果失敗. 檢查是否有加入效果.");
                    System.exit(0);
                }

                return;
            }
        }
    }

    public void checkItemsEffect() {
        for (Effect effect : effects) {
            effect.check();
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
