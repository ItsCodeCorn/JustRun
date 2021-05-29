package rungame.game;

import javax.swing.SwingUtilities;

import rungame.framework.Engine;
import rungame.framework.resources.ResourcesLoader;

public class RunGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
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