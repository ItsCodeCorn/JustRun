package rungame.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import rungame.framework.Engine;
import rungame.framework.gui.Input;
import rungame.framework.resources.Text;

public class MenuState extends State {
    private int index;
    private Text title;
    private Text start;
    private Text exit;

    public MenuState(StateManager stateManager) {
        super(stateManager);

        this.title = new Text("Just Run!");
        this.start = new Text("START");
        this.exit = new Text("EXIT");
    }

    @Override
    public void init() {
        System.out.println("[執行階段][MenuState] init 執行中...");
        this.index = 0;

        this.title.setColor(200, 0, 0);
        this.title.setFont(Font.SANS_SERIF, Font.BOLD, 180);

        this.start.setColor(0, 0, 0);
        this.start.setFont(Font.MONOSPACED, Font.BOLD, 100);

        this.exit.setColor(0, 0, 0);
        this.exit.setFont(Font.MONOSPACED, Font.BOLD, 100);
        System.out.println("[執行階段][MenuState] init 執行完成!");
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
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 180);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int x = 0 + (1280 - fontMetrics.stringWidth("Just Run!")) / 2;
        int y = 0 + ((720 - fontMetrics.getHeight()) / 2) + 100;

        this.title.draw(g, x, y - 100);

        font = new Font(Font.MONOSPACED, Font.BOLD, 100);
        fontMetrics = g.getFontMetrics(font);
        x = 0 + (1280 - fontMetrics.stringWidth("START")) / 2;
        y = 0 + ((720 - fontMetrics.getHeight()) / 2) + 100;

        this.start.draw(g, x, y);

        x = 0 + (1280 - fontMetrics.stringWidth("EXIT")) / 2;
        this.exit.draw(g, x, y + 150);
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
                this.stateManager.nextState(new ChooseMapState(this.stateManager));
                break;

            case 1:
                System.exit(0);
                break;
            }
        }
    }

    private void checkIndex() {
        switch (index) {
        case 0:
            this.start.setColor(160, 160, 160);
            this.exit.setColor(0, 0, 0);
            break;

        case 1:
            this.start.setColor(0, 0, 0);
            this.exit.setColor(160, 160, 160);
            break;
        }
    }
}
