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
    private Text resume;
    private Text exit;
    private boolean init;

    public PauseState(StateManager stateManager) {
        super(stateManager);

        this.resume = new Text("Resume");
        this.exit = new Text("Exit");
    }

    @Override
    public void init() {
        System.out.println("[執行階段][PauseState] init 執行中...");
        this.index = 0;
        this.init = true;

        this.resume.setColor(160, 160, 160);
        this.resume.setFont(Font.MONOSPACED, Font.BOLD, 50);

        this.exit.setColor(160, 160, 160);
        this.exit.setFont(Font.MONOSPACED, Font.BOLD, 50);
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
        if (this.init) {
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, 1280, 720);
            this.init = false;
        }

        g.setColor(new Color(0, 0, 0));
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 50);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int x = 0 + (1280 - fontMetrics.stringWidth("Resume")) / 2;
        int y = 0 + ((720 - fontMetrics.getHeight()) / 2);

        this.resume.draw(g, x, y);

        x = 0 + (1280 - fontMetrics.stringWidth("Exit")) / 2;
        this.exit.draw(g, x, y + 50);
    }

    @Override
    public void input() {
        if (Input.isPressedOnce(KeyEvent.VK_W) || Input.isPressedOnce(KeyEvent.VK_UP)) {
            if (index > 0) {
                --index;
            }
        }

        if (Input.isPressedOnce(KeyEvent.VK_S) || Input.isPressedOnce(KeyEvent.VK_DOWN)) {
            if (index < 1) {
                ++index;
            }
        }

        if (Input.isPressedOnce(KeyEvent.VK_ENTER)) {
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
            this.resume.setColor(255, 0, 255);
            this.exit.setColor(160, 160, 160);
            break;

        case 1:
            this.resume.setColor(160, 160, 160);
            this.exit.setColor(255, 0, 255);
            break;
        }
    }
}
