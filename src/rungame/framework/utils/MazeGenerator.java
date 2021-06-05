package rungame.framework.utils;

import java.util.ArrayList;

import rungame.game.map.Map;

import java.awt.Point;

public class MazeGenerator {
    public static final int RANDOMIZED_DFS = 0;
    public static final int RANDOMIZED_PRIM = 1;

    private static void init(Map map) {
        for (int x = 0; x < map.getWidth(); ++x) {
            for (int y = 0; y < map.getHeight(); ++y) {
                map.setChar(x, y, '*');
            }
        }
    }

    private static void randomizedDfs(Map map, int prevX, int prevY, int x, int y) {
        if (x < 1 || x > map.getWidth() - 2 || y < 1 || y > map.getHeight() - 2) {
            return;
        }

        if (map.getChar(x, y) == ' ') {
            return;
        }

        if (prevX != x) {
            if (prevX < x) {
                map.setChar(x - 1, y, ' ');
            } else {
                map.setChar(x + 1, y, ' ');
            }
        }

        if (prevY != y) {
            if (prevY < y) {
                map.setChar(x, y - 1, ' ');
            } else {
                map.setChar(x, y + 1, ' ');
            }
        }
        map.setChar(x, y, ' ');

        Point[] direction = {new Point(2, 0), new Point(-2, 0), new Point(0, 2), new Point(0, -2)};

        for (int i = 0; i < 4; ++i) {
            int rd = (int)(Math.random() * (i + 1));

            Point tmp = direction[i];
            direction[i] = direction[rd];
            direction[rd] = tmp;
        }

        for (int i = 0; i < 4; ++i) {
            randomizedDfs(map, x, y, x + direction[i].x, y + direction[i].y);
        }
    }

    private static class Point_ {
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

    private static void randomizedPrim(Map map) {
        ArrayList<Point_> list = new ArrayList<>();

        list.add(new Point_(1, 1, 1, 1));

        while (!list.isEmpty()) {
            int rdP = (int)(Math.random() * list.size());
            Point_ point = list.get(rdP);
            list.remove(rdP);

            if (point.x < 1 || point.x > map.getWidth() - 2 || point.y < 1 || point.y > map.getHeight() - 2) {
                continue;
            }

            if (map.getChar(point.x, point.y) == ' ') {
                continue;
            }

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
                int rd = (int)(Math.random() * (j + 1));

                Point tmp = direction[j];
                direction[j] = direction[rd];
                direction[rd] = tmp;
            }

            for (int j = 0; j < 4; ++j) {
                list.add(new Point_(point.x, point.y, point.x + direction[j].x, point.y + direction[j].y));
            }
        }
    }

    public static Map generateMaze(int width, int height, int type) {
        System.out.println("[執行階段][MazeGenerator] generateMaze 執行中...");
        Map map = new Map(width, height);

        init(map);
        switch (type) {
        case RANDOMIZED_DFS:
            randomizedDfs(map, 1, 1, 1, 1);
            break;

        case RANDOMIZED_PRIM:
            randomizedPrim(map);
            break;

        default:
            System.out.println("[錯誤][MazeGenerator] 無此生成種類!");
            System.exit(0);
            break;
        }

        System.out.println("[執行階段][MazeGenerator] generateMaze 執行完成!");
        return map;
    }
}

