package rungame.game.states;

import java.util.ArrayList;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

import rungame.framework.Engine;
import rungame.framework.resources.Resources;
import rungame.framework.resources.Sprite;
import rungame.game.map.Map;

public class GenerateMazeState extends State {
    private Map map;
    private Sprite wall;
    private boolean completed;
    private ArrayList<Point_> list = new ArrayList<>();

    public GenerateMazeState(StateManager stateManager) {
        super(stateManager);

        this.completed = false;
        this.map = new Map(49, 25);
        this.wall = Resources.getSprite(Resources.WALL);
    }

    @Override
    public void init() {
        System.out.println("[執行階段][GenerateMazeState] init 執行中...");

        mapInit(map);
        list.add(new Point_(1, 1, 1, 1));

        System.out.println("[執行階段][GenerateMazeState] init 執行完成!");
    }

    @Override
    public void tick() {
        this.input();

        this.generateNext();

        Engine.render();

        this.checkCompleted();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, 1280, 720);

        g.setColor(new Color(50, 50, 50));
        g.fillRect(0, 0, map.getWidth() * 25, map.getHeight() * 25);

        for (int y = 0; y < map.getHeight(); ++y) {
            for (int x = 0; x < map.getWidth(); ++x) {
                if (map.getChar(x, y) == '*' || map.getChar(x, y) == '_') {
                    wall.draw(g, x * 25, y * 25);
                }
            }
        }
    }

    @Override
    public void input() {

    }

    private void mapInit(Map map) {
        for (int x = 0; x < map.getWidth(); ++x) {
            for (int y = 0; y < map.getHeight(); ++y) {
                map.setChar(x, y, '*');
            }
        }
    }

    private class Point_ {
        public int prevX;
        public int prevY;
        public int x;
        public int y;

        public Point_(int prevX, int prevY, int x, int y) {
            this.prevX = prevX;
            this.prevY = prevY;
            this.x = x;
            this.y = y;
        }
    }

    private void generateNext() {
        if (list.isEmpty()) {
            this.completed = true;
            return;
        }

        int rdP = (int)(Math.random() * list.size());
        Point_ point = list.get(rdP);
        list.remove(rdP);

        map.setChar(point.x, point.y, ' ');

        if (point.prevX != point.x) {
            if (point.prevX < point.x) {
                map.setChar(point.x - 1, point.y, ' ');
            } else {
                map.setChar(point.x + 1, point.y, ' ');
            }
        }
        if (point.prevY != point.y) {
            if (point.prevY < point.y) {
                map.setChar(point.x, point.y - 1, ' ');
            } else {
                map.setChar(point.x, point.y + 1, ' ');
            }
        }

        Point[] direction = {new Point(2, 0), new Point(-2, 0), new Point(0, 2), new Point(0, -2)};

        for (int j = 0; j < 4; ++j) {
            if (point.x + direction[j].x < 1 || point.x + direction[j].x > map.getWidth() - 2 || point.y + direction[j].y < 1 || point.y + direction[j].y > map.getHeight() - 2) {
                continue;
            }

            if (map.getChar(point.x + direction[j].x, point.y + direction[j].y) == ' ' || map.getChar(point.x + direction[j].x, point.y + direction[j].y) == '_') {
                continue;
            }

            map.setChar(point.x + direction[j].x, point.y + direction[j].y, '_');

            list.add(new Point_(point.x, point.y, point.x + direction[j].x, point.y + direction[j].y));
        }
    }

    private void checkCompleted() {
        if (!completed) {
            return;
        }

        Engine.sleep();
        stateManager.backState();
        stateManager.nextState(new PlayingState(this.stateManager, this.map));
        Engine.wake();
    }
}
