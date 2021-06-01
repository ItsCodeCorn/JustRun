package rungame.framework;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

import rungame.framework.gui.GameScreen;
import rungame.framework.gui.ScreenManager;
import rungame.game.entities.Player;
import rungame.game.states.PlayingState;

public class Engine {
    private static PlayingState playingState;
    private static ScreenManager screenManager;
    private static Timer timer;
    public static final int TICK = 30; // 0.03s
    //private static boolean running;
    public static final long SUMMON_MONSTER_TIME = (long)(10.0d * 1000);
    public static final long SUMMON_ITEM_TIME = (long)(5.0d * 1000);
    public static final long MONSTER_MOVE_TIME = (long)(0.25d * 1000);
    public static final long PLAYER_MOVE_TIME = (long)(0.15d * 1000);

    public static void init() {
        System.out.print("\033[H\033[2J");
        System.out.println("[執行階段][Engine] init 執行中...");

        screenManager = new ScreenManager();

        timer = new Timer(
            TICK, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    playingState.tick();
                    update();
                }
            }
        );

        timer.setInitialDelay(0);
        System.out.println("[執行階段][Engine] init 執行完成.");
    }

    public static void start() {
        playingState = new PlayingState();
        playingState.init();

        screenManager.addPanel(new GameScreen(playingState));
        screenManager.setInput();
        screenManager.createWindow();

        timer.start();
    }

    public static void update() {
        screenManager.update();
    }

    public static void stop() {
        timer.stop();
    }

    public static PlayingState getPlayingState() {
        return playingState;
    }

    public static Player getPlayer() {
        return playingState.getPlayer();
    }
}
