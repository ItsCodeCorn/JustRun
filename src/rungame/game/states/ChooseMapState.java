package rungame.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import rungame.framework.Engine;
import rungame.framework.gui.Input;
import rungame.framework.resources.Text;
import rungame.framework.utils.MazeGenerator;

public class ChooseMapState extends State {
    private int index;
    private Text skip;
    private Text generateMaze;
    private Text customMap;
    private Text back;

    public ChooseMapState(StateManager stateManager) {
        super(stateManager);

        this.skip = new Text("(Skip)");
        this.generateMaze = new Text("GenerateMaze");
        this.customMap = new Text("CustomMap");
        this.back = new Text("Back");
    }

    @Override
    public void init() {
        System.out.println("[執行階段][ChooseMapState] init 執行中...");
        this.index = 0;

        this.skip.setColor(0, 0, 0);
        this.skip.setFont(Font.MONOSPACED, Font.BOLD, 25);

        this.generateMaze.setColor(0, 0, 0);
        this.generateMaze.setFont(Font.MONOSPACED, Font.BOLD, 50);

        this.customMap.setColor(0, 0, 0);
        this.customMap.setFont(Font.MONOSPACED, Font.BOLD, 50);

        this.back.setColor(0, 0, 0);
        this.back.setFont(Font.MONOSPACED, Font.BOLD, 50);

        System.out.println("[執行階段][ChooseMapState] init 執行完成!");
    }

    @Override
    public void tick() {
        this.input();

        this.checkIndex();

        Engine.render();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(0, 120, 200));
        g.fillRect(0, 0, 2000, 2000);

        g.setColor(new Color(0, 0, 0));
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 50);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int x = 0 + (1280 - fontMetrics.stringWidth("GenerateMaze")) / 2;
        int y = 0 + ((720 - fontMetrics.getHeight()) / 2) + 100;

        this.generateMaze.draw(g, x, y);

        x = 0 + (1280 - fontMetrics.stringWidth("CustomMap")) / 2;
        this.customMap.draw(g, x, y + 50);

        x = 0 + (1280 - fontMetrics.stringWidth("Back")) / 2;
        this.back.draw(g, x, y + 100);

        x = fontMetrics.stringWidth("GenerateMaze") + (1280 - fontMetrics.stringWidth("GenerateMaze")) / 2;

        x = x + 40;
        this.skip.draw(g, x + 20, y);
    }

    @Override
    public void input() {
        if (index == 0 || index == -1) {
            if (Input.isPressedOnce(KeyEvent.VK_A) || Input.isPressedOnce(KeyEvent.VK_LEFT)) {
                if (index == -1) {
                    index = 0;
                }
            }

            if (Input.isPressedOnce(KeyEvent.VK_D) || Input.isPressedOnce(KeyEvent.VK_RIGHT)) {
                if (index == 0) {
                    index = -1;
                }
            }
        }

        if (Input.isPressedOnce(KeyEvent.VK_W) || Input.isPressedOnce(KeyEvent.VK_UP)) {
            if (index > 0) {
                --index;
            }
        }

        if (Input.isPressedOnce(KeyEvent.VK_S) || Input.isPressedOnce(KeyEvent.VK_DOWN)) {
            if (index < 2) {
                ++index;
            }
        }

        if (Input.isPressedOnce(KeyEvent.VK_ENTER)) {
            Engine.sleep();
            stateManager.backState();

            switch (index) {
            case -1:
                stateManager.nextState(new PlayingState(stateManager, MazeGenerator.generateMaze(49, 25, MazeGenerator.RANDOMIZED_PRIM)));
                break;

            case 0:
                stateManager.nextState(new GenerateMazeState(stateManager));
                break;

            case 1:
                stateManager.nextState(new PlayingState(stateManager, null));
                break;

            case 2:
                break;
            }

            Engine.wake();
        }
    }

    public void checkIndex() {
        switch (index) {
        case -1:
            this.skip.setColor(160, 160, 160);
            this.generateMaze.setColor(0, 0, 0);
            this.customMap.setColor(0, 0, 0);
            this.back.setColor(0, 0, 0);
            break;

        case 0:
            this.skip.setColor(0, 0, 0);
            this.generateMaze.setColor(160, 160, 160);
            this.customMap.setColor(0, 0, 0);
            this.back.setColor(0, 0, 0);
            break;

        case 1:
            this.skip.setColor(0, 0, 0);
            this.generateMaze.setColor(0, 0, 0);
            this.customMap.setColor(160, 160, 160);
            this.back.setColor(0, 0, 0);
            break;

        case 2:
            this.skip.setColor(0, 0, 0);
            this.generateMaze.setColor(0, 0, 0);
            this.customMap.setColor(0, 0, 0);
            this.back.setColor(160, 160, 160);
            break;
        }
    }
}
