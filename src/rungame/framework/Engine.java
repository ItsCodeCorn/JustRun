package rungame.framework;

import rungame.framework.gui.GameScreen;
import rungame.framework.gui.ScreenManager;
import rungame.game.states.MenuState;
import rungame.game.states.StateManager;

public class Engine {
    private static StateManager stateManager;
    private static ScreenManager screenManager;
    private static Thread thread;
    private static boolean running;
    public static final int TICK = 15; // 0.02s
    public static final int SUMMON_MONSTER_TIME = (int)(20.00d * (1000 / TICK));
    public static final int SUMMON_ITEM_TIME = (int)(10.00d * (1000 / TICK));
    public static final int MONSTER_MOVE_TIME = (int)(0.20d * (1000 / TICK));
    public static final int PLAYER_MOVE_TIME = (int)(0.13d * (1000 / TICK));

    public static void init() {
        System.out.print("\033[H\033[2J");
        System.out.println("[執行階段][Engine] init 執行中...");

        screenManager = new ScreenManager();
        stateManager = new StateManager();

        running = false;

        thread = new Thread(
            new Runnable() {
                private long previousTime = System.currentTimeMillis();
                private long currentTime;
                @Override
                public void run() {
                    while (true) {
                        while (running) {
                            currentTime = System.currentTimeMillis();

                            if (currentTime - previousTime >= TICK) {
                                stateManager.tick();
                                render();

                                previousTime = currentTime;
                            }
                        }
                    }
                }
            }
        );

        System.out.println("[執行階段][Engine] init 執行完成!");
    }

    public static void start() {
        System.out.println("[執行階段][Engine] start 執行中...");
        stateManager.nextState(new MenuState(stateManager));

        screenManager.addPanel(new GameScreen());
        screenManager.setInput();
        screenManager.createWindow();

        running = true;
        thread.start();
        System.out.println("[執行階段][Engine] start 執行完成!");
    }

    public static void render() {
        screenManager.render();
    }

    public static void sleep() {
        running = false;
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
        }
    }
    public static void wake() {
        running = true;
    }

    public static StateManager getStateManager() {
        return stateManager;
    }
}
