package rungame.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import rungame.framework.Engine;
import rungame.framework.gui.Input;
import rungame.framework.resources.Text;

public class PauseState extends State {
    private int index;
    private boolean enterReset;
    private Text pause;
    private Text exit;

    public PauseState(StateManager stateManager) {
        super(stateManager);

        this.enterReset = false;

        this.pause = new Text("PAUSE");
        this.exit = new Text("EXIT");
    }

    @Override
    public void init() {
        System.out.println("[執行階段][PauseState] init 執行中...");
        this.index = 0;

        this.pause.setColor(0, 0, 0);
        this.pause.setFont(Font.MONOSPACED, Font.BOLD, 100);

        this.exit.setColor(0, 0, 0);
        this.exit.setFont(Font.MONOSPACED, Font.BOLD, 100);
        System.out.println("[執行階段][PauseState] init 執行完成!");
    }

    @Override
    public void tick() {
        this.input();

        this.checkIndex();

        Engine.render();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(240, 240, 240));
        g.fillRect((1280 - 400) / 2, (720 - 300) / 2, 400, 300);

        g.setColor(new Color(0, 0, 0));
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 100);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int x = 0 + (1280 - fontMetrics.stringWidth("START")) / 2;
        int y = 0 + ((720 - fontMetrics.getHeight()) / 2) + 20;

        this.pause.draw(g, x, y);

        x = 0 + (1280 - fontMetrics.stringWidth("EXIT")) / 2;
        this.exit.draw(g, x, y + 150);
    }

    @Override
    public void input() {
        if (Input.isReleased(KeyEvent.VK_ENTER)) {
            enterReset = true;
        }

        if (Input.isPressed(KeyEvent.VK_W) || Input.isPressed(KeyEvent.VK_UP)) {
            if (index > 0) {
                --index;
            }
        }

        if (Input.isPressed(KeyEvent.VK_S) || Input.isPressed(KeyEvent.VK_DOWN)) {
            if (index < 1) {
                ++index;
            }
        }

        if (enterReset && Input.isPressed(KeyEvent.VK_ENTER)) {
            switch (index) {
            case 0:
                stateManager.backState();
                break;

            case 1:
                System.exit(0);
                break;
            }
        }
    }

    public void checkIndex() {
        switch (index) {
        case 0:
            this.pause.setColor(160, 160, 160);
            this.exit.setColor(0, 0, 0);
            break;

        case 1:
            this.pause.setColor(0, 0, 0);
            this.exit.setColor(160, 160, 160);
            break;
        }
    }
}
