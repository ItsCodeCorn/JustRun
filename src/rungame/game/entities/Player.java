package rungame.game.entities;

import java.util.LinkedList;

import rungame.framework.Engine;
import rungame.framework.gui.Input;
import rungame.framework.resources.Resources;
import rungame.framework.utils.Counter;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Player extends Entity {
    private boolean invulnerable;
    public Player(int x, int y) {
        super(Resources.PLAYER, 'P', new Rectangle(x, y, 25, 25));

        this.invulnerable = false;
        this.moveTimeCounter = new Counter(0);

        this.hasCollision = new LinkedList<>();
        this.hasCollision.addFirst('*');
        this.hasCollision.addFirst('M');
    }

    @Override
    public void action() {
        if (this.moveTimeCounter.count()) {
            this.moveTimeCounter.setEndCount(0);

            if (Input.isPressed(KeyEvent.VK_UP) || Input.isPressed(KeyEvent.VK_W)) {
                this.speed.translate(0, -25);
                this.moveTimeCounter.setEndCount(Engine.PLAYER_MOVE_TIME);
            }

            if (Input.isPressed(KeyEvent.VK_DOWN) || Input.isPressed(KeyEvent.VK_S)) {
                this.speed.translate(0, 25);
                this.moveTimeCounter.setEndCount(Engine.PLAYER_MOVE_TIME);
            }

            if (Input.isPressed(KeyEvent.VK_LEFT) || Input.isPressed(KeyEvent.VK_A)) {
                this.speed.translate(-25, 0);
                this.moveTimeCounter.setEndCount(Engine.PLAYER_MOVE_TIME);
            }

            if (Input.isPressed(KeyEvent.VK_RIGHT) || Input.isPressed(KeyEvent.VK_D)) {
                this.speed.translate(25, 0);
                this.moveTimeCounter.setEndCount(Engine.PLAYER_MOVE_TIME);
            }

            this.moveDistance(speed.x, speed.y);
            this.speed.setLocation(0, 0);
        }
    }

    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
    }
    public boolean isInvulnerable() {
        return this.invulnerable;
    }
}
