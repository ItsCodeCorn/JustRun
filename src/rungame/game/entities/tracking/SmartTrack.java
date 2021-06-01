package rungame.game.entities.tracking;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

import rungame.framework.Engine;
import rungame.game.entities.Monster;

public class SmartTrack extends MonsterTrackStrategy {
    private Monster monster;
    private Point playerLoc;
    private Point monsterLoc;
    private int width, height;
    private char map[][];

    private class Point_ {
        public Point_(int x, int y, Point_ prev) {
            this.x = x;
            this.y = y;
            this.prev = prev;
        }

        public int x;
        public int y;
        public Point_ prev;
    }

    public void track(Monster monster) {
        this.monster = monster;
        playerLoc = monster.getPlayer().getLocation();
        monsterLoc = monster.getLocation();
        width = Engine.getPlayingState().getMapWidth();
        height = Engine.getPlayingState().getMapHeight();
        map = new char[width][height];

        playerLoc.x /= 25;
        playerLoc.y /= 25;
        monsterLoc.x /= 25;
        monsterLoc.y /= 25;

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                map[x][y] = Engine.getPlayingState().getMapChar(x, y);
            }
        }

        Point_ point = bfs();
        monster.moveTo(point.x * 25, point.y * 25);
    }

    private Point_ bfs() {
        Queue<Point_> q = new LinkedList<>();

        Point_ now = null;
        Point_[] direction = {new Point_(1, 0, null), new Point_(-1, 0, null), new Point_(0, 1, null), new Point_(0, -1, null), new Point_(-1, -1, null), new Point_(-1, 1, null), new Point_(1, -1, null), new Point_(1, 1, null)};
        Point_ p = new Point_(monsterLoc.x, monsterLoc.y, null);
        q.add(p);

        Outer:
        while (!q.isEmpty()) {
            int s = q.size();

            for (int i = 0; i < s; ++i) {
                now = q.poll();

                for (int j = 0; j < 8; ++j) {
                    int rd = (int)Math.floor(Math.random() * j);

                    Point_ tmp = direction[rd];
                    direction[rd] = direction[j];
                    direction[j] = tmp;
                }

                NextMove:
                for (int j = 0; j < 8; ++j) {
                    for (char sign : monster.getHasCollision()) {
                        if (map[now.x + direction[j].x][now.y + direction[j].y] == sign) {
                            continue NextMove;
                        }
                    }

                    if (direction[j].x != 0 && direction[j].y != 0) {
                        int c = 0;
                        for (char sign : monster.getHasCollision()) {
                            if (map[now.x + direction[j].x][now.y] == sign) {
                                ++c;
                                break;
                            }
                        }
                        for (char sign : monster.getHasCollision()) {
                            if (map[now.x][now.y + direction[j].y] == sign) {
                                ++c;
                                break;
                            }
                        }

                        if (c == 2) {
                            continue NextMove;
                        }
                    }

                    if (map[now.x + direction[j].x][now.y + direction[j].y] == 'P') {
                        now = new Point_(now.x + direction[j].x, now.y + direction[j].y, now);
                        break Outer;
                    }

                    map[now.x + direction[j].x][now.y + direction[j].y] = '*';

                    p = new Point_(now.x + direction[j].x, now.y + direction[j].y, now);
                    q.add(p);
                }
            }
        }

        while (!(now.prev.x == monsterLoc.x && now.prev.y == monsterLoc.y)) {
            now = now.prev;
        }

        return now;
    }
}