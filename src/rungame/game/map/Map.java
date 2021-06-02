package rungame.game.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.awt.Point;

public class Map {
    private char[][] map;
    private int width;
    private int height;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;

        map = new char[width][height];
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public char getChar(int x, int y) {
        return map[x][y];
    }
    public void setChar(int x, int y, char c) {
        map[x][y] = c;
    }

    public Point getPlayerLocation() {
        Point playerLoc = new Point(1, 1);

        Outer:
        for (int y = 0; y < this.height; ++y) {
            for (int x = 0; x < this.width; ++x) {
                if (map[x][y] == 'P') {
                    playerLoc = new Point(x, y);
                    break Outer;
                }
            }
        }

        return playerLoc;
    }

    public static Map loadMap(String filename) {
        System.out.println("[執行階段][Map] loadMap 執行中...");
        Map m = new Map(1, 1);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("res/levels/" + filename + ".txt")));

            m = new Map(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()));

            for (int y = 0; y < m.height; ++y) {
                String line = reader.readLine();

                for (int x = 0; x < m.width; ++x) {
                    m.map[x][y] = line.charAt(x);
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("[錯誤][Map] 讀取地圖失敗. 請檢查路徑是否正確.");
            System.exit(0);
        } catch (RuntimeException e) {
            System.out.println("[錯誤][Map] 讀取地圖失敗. 請檢查格式是否正確.");
            System.exit(0);
        }

        System.out.println("[執行階段][Map] loadMap 執行完成!");
        return m;
    }
}