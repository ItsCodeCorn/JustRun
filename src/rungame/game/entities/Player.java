package rungame.game.entities;

import java.util.LinkedList;

import rungame.framework.Engine;
import rungame.framework.gui.Input;
import rungame.framework.resources.Resources;
import rungame.framework.utils.Counter;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Player extends Entity {
    public Player(int x, int y) {
        super(Resources.PLAYER, 'P', new Rectangle(x, y, 25, 25));

        moveTimeCounter = new Counter(0L);

        hasCollision = new LinkedList<>();
        hasCollision.addFirst('*');
        hasCollision.addFirst('M');
    }

    @Override
    public void action() {
        if (moveTimeCounter.count()) {
            moveTimeCounter.setDuration(0);

            if (Input.isPressed(KeyEvent.VK_UP) || Input.isPressed(KeyEvent.VK_W)) {
                speed.translate(0, -25);
                moveTimeCounter.setDuration(Engine.PLAYER_MOVE_TIME);
            }

            if (Input.isPressed(KeyEvent.VK_DOWN) || Input.isPressed(KeyEvent.VK_S)) {
                speed.translate(0, 25);
                moveTimeCounter.setDuration(Engine.PLAYER_MOVE_TIME);
            }

            if (Input.isPressed(KeyEvent.VK_LEFT) || Input.isPressed(KeyEvent.VK_A)) {
                speed.translate(-25, 0);
                moveTimeCounter.setDuration(Engine.PLAYER_MOVE_TIME);
            }

            if (Input.isPressed(KeyEvent.VK_RIGHT) || Input.isPressed(KeyEvent.VK_D)) {
                speed.translate(25, 0);
                moveTimeCounter.setDuration(Engine.PLAYER_MOVE_TIME);
            }

            moveDistance(speed.x, speed.y);
            speed.setLocation(0, 0);
        }
    }
}
