package rungame.framework;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

import rungame.framework.gui.GameScreen;
import rungame.framework.gui.Input;
import rungame.framework.gui.ScreenManager;
import rungame.game.entities.Player;
import rungame.game.states.PlayingState;

public class Engine {
    private static PlayingState playingState;
    private static ScreenManager screenManager;
    private static Timer timer;
    public static final int TICK = 60; // 0.05s
    private static boolean running;
    public static final int SUMMON_MONSTER_TIME = (int)(10.0d * (1000.0d / TICK));
    public static final int SUMMON_ITEM_TIME = (int)(5.0d * (1000.0d / TICK));
    public static final int MONSTER_MOVE_TIME = (int)(0.22d * (1000.0d / TICK));
    public static final int PLAYER_MOVE_TIME = (int)(0.12d * (1000.0d / TICK));

    public static void init() {
        screenManager = new ScreenManager();

        timer = new Timer(
            TICK, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    playingState.tick();
                    render();
                }
            }
        );

        timer.setInitialDelay(0);
    }

    public static void start() {
        playingState = new PlayingState();
        playingState.init();

        screenManager.addPanel(new GameScreen(playingState));
        screenManager.setInput();
        screenManager.createWindow();

        timer.start();
    }

    public static void render() {
        screenManager.render();
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
