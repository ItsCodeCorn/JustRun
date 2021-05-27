package rungame;

import javax.swing.Timer;

import java.awt.EventQueue;

public class RunGame {
    private static Display display;
    private static Game game;
    private static GamePanel gamePanel;
    private static boolean running;
    private static Input input;
    private static Timer timer;
    public static final int TICK = 10;
    public static final int SUMMON_MONSTER_TIME = (int)(10.0d * (1000.0d / TICK));
    public static final int MONSTER_MOVE_TIME = (int)(0.2d * (1000.0d / TICK));
    public static final int PLAYER_MOVE_TIME = (int)(0.1d * (1000.0d / TICK));

    /*public static final int TICK = 10;
    public static final int TICK = 10;
    public static final int TICK = 10;
    public static final int TICK = 10;*/
    public static void main(String[] args) {
        EventQueue.invokeLater(
            new Runnable() {
                @Override
                public void run() {
                    display = new Display();
                    game = new Game();
                    gamePanel = new GamePanel(game);
                    input = new Input();

                    display.setContentPane(gamePanel);
                    gamePanel.addKeyListener(input);

                    startGame();
                }
            }
        );
    }

    private static void startGame() {
        game.init();
        running = true;

        timer = new Timer(RunGame.TICK, new GameLoop(game));
        timer.setInitialDelay(0);
        timer.start();
    }

    public static Game getGame() {
        return game;
    }
    public static GamePanel getGamePanel() {
        return gamePanel;
    }
    public static Display getDisplay() {
        return display;
    }
    public static boolean isRunning() {
        return running;
    }
    public static Input getInput() {
        return input;
    }
    public static Timer getTimer() {
        return timer;
    }
}