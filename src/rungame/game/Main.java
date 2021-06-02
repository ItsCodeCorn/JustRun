package rungame.game;

import java.awt.EventQueue;

import rungame.framework.Engine;
import rungame.framework.resources.ResourcesLoader;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(
            new Runnable() {
                @Override
                public void run() {
                    ResourcesLoader.load();

                    Engine.init();
                    Engine.start();
                }
            }
        );
    }
}