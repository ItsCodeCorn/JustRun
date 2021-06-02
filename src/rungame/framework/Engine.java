package rungame.framework;

import rungame.framework.gui.GameScreen;
import rungame.framework.gui.ScreenManager;
import rungame.game.entities.Player;
import rungame.game.states.PlayingState;

public class Engine {
    private static PlayingState playingState;
    private static ScreenManager screenManager;
    private static Thread thread;
    public static boolean running;
    public static final int TICK = 20; // 0.02s
    public static final int SUMMON_MONSTER_TIME = (int)(10.00d * (1000 / TICK));
    public static final int SUMMON_ITEM_TIME = (int)(5.00d * (1000 / TICK));
    public static final int MONSTER_MOVE_TIME = (int)(0.20d * (1000 / TICK));
    public static final int PLAYER_MOVE_TIME = (int)(0.12d * (1000 / TICK));

    public static void init() {
        System.out.print("\033[H\033[2J");
        System.out.println("[執行階段][Engine] init 執行中...");

        screenManager = new ScreenManager();

        running = false;

        thread = new Thread(
            new Runnable() {
                private long previousTime = System.currentTimeMillis();
                private long currentTime;
                @Override
                public void run() {
                    while (running) {
                        currentTime = System.currentTimeMillis();

                        if (currentTime - previousTime >= TICK) {
                            playingState.tick();
                            render();

                            previousTime = currentTime;
                        }
                    }
                }
            }
        );

        System.out.println("[執行階段][Engine] init 執行完成!");
    }

    public static void start() {
        System.out.println("[執行階段][Engine] start 執行中...");
        playingState = new PlayingState();
        playingState.init();

        screenManager.addPanel(new GameScreen(playingState));
        screenManager.setInput();
        screenManager.createWindow();

        running = true;
        thread.start();
        System.out.println("[執行階段][Engine] start 執行完成!");
    }

    public static void render() {
        screenManager.render();
    }

    public static void stop() {
        running = false;
    }

    public static PlayingState getPlayingState() {
        return playingState;
    }

    public static Player getPlayer() {
        return playingState.getPlayer();
    }
}
