package rungame.game.states;

import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Math;
import java.util.Vector;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import rungame.framework.Engine;
import rungame.framework.gui.Input;
import rungame.framework.resources.Resources;
import rungame.framework.utils.Counter;
import rungame.framework.utils.MazeGenerator;
import rungame.game.effects.*;
import rungame.game.entities.*;
import rungame.game.entities.items.*;
import rungame.game.factories.EntityFactory;
import rungame.game.map.Map;

public class PlayingState extends State {
    private long startTime;
    private Map map;
    private Player player;
    private LinkedList<Entity> entities;
    private LinkedList<Wall> walls;
    private LinkedList<EffectItem> items;
    private ArrayList<Effect> effects;
    private Counter summonMonsterCounter;
    private Counter summonItemCounter;
    private boolean gameOver;
    private boolean pReset;

    public PlayingState(StateManager stateManager, Map map) {
        super(stateManager);
        this.map = map;
        entities = new LinkedList<>();
        walls = new LinkedList<>();
        items = new LinkedList<>();
        effects = new ArrayList<>();

        summonMonsterCounter = new Counter(Engine.SUMMON_MONSTER_TIME);
        summonItemCounter = new Counter(Engine.SUMMON_ITEM_TIME);
        gameOver = false;
    }

    @Override
    public void init() {
        System.out.println("[執行階段][PlayingState] init 執行中...");

        if (map == null) {
            map = Map.loadMap("Maze");
            //map = MazeGenerator.generateMaze(49, 25, MazeGenerator.RANDOMIZED_PRIM);
        }

        Point playerLoc = map.getPlayerLocation();

        player = EntityFactory.createPlayer(playerLoc.x, playerLoc.y, this);
        player.printMap();
        entities.addFirst(player);

        addAllWall();

        effects.add(new SpeedUpPlayerEffect(this));
        effects.add(new SpeedDownMonsterEffect(this));
        effects.add(new EliminateMonstersEffect(this));
        effects.add(new ScareMonstersEffect(this));

        summonMonsterCounter.setEndCount(0);
        summonMonster();
        summonMonsterCounter.setEndCount(Engine.SUMMON_MONSTER_TIME);
        startTime = System.currentTimeMillis();
        System.out.println("[執行階段][PlayingState] init 執行完成!");
    }

    @Override
    public void tick() {
        input();

        entities.forEach(entity -> entity.action());
        printAllItems();

        checkMonstersCollision();
        checkGameOver();

        checkPickUpItems();
        checkItemsEffect();

        summonMonster();
        summonItem();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, 1280, 720);

        g.setColor(new Color(50, 50, 50));
        g.fillRect(0, 0, getMapWidth() * 25, getMapHeight() * 25);

        items.forEach(entity -> entity.draw(g));
        entities.forEach(entity -> entity.draw(g));
        walls.forEach(entity -> entity.draw(g));

        int effectsCount = 0;
        for (Effect effect : effects) {
            if (effect.isTrigger()) {
                Resources.getSprite(effect.getResourceId()).draw(g, 150 + (25 * effectsCount), 640);
                ++effectsCount;
            }
        }

        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.setColor(new Color(200, 0, 255));
        g.drawString("Times: " + Double.toString((System.currentTimeMillis() - startTime) / 1000.0d), 0, 660);
    }

    @Override
    public void input() {
        if (Input.isReleased(KeyEvent.VK_P)) {
            pReset = true;
        }

        if (pReset && Input.isPressed(KeyEvent.VK_P)) {
            pReset = false;

            stateManager.nextState(new PauseState(stateManager));
        }
    }

    private void addAllWall() {
        for (int x = 0; x < map.getWidth(); ++x) {
            for (int y = 0; y < map.getHeight(); ++y) {
                if (map.getChar(x, y) == '*') {
                    walls.addFirst(EntityFactory.createWall(x, y, this));
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

        Engine.sleep();
        stateManager.nextState(new GameOverState(stateManager));
        Engine.wake();
    }

    public void summonMonster() {
        if (!summonMonsterCounter.count()) {
            return;
        }

        Point loc = getRandomLocation();

        if (loc == null) {
            return;
        }

        Monster monster = EntityFactory.createMonster(loc.x, loc.y, this);
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

        EffectItem item = EntityFactory.createRandomItem(loc.x, loc.y, this);
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
