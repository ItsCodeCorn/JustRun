package rungame.game.entities;

import java.util.LinkedList;

import rungame.RunGame;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Player extends Entity {
    public Player(int x, int y) {
        super("Player", 'P', new Rectangle(x, y, 25, 25));

        moveTime = RunGame.PLAYER_MOVE_TIME;

        hasCollision = new LinkedList<>();
        hasCollision.addFirst('*');
        hasCollision.addFirst('M');
    }

    @Override
    public void action() {
        if (canMove()) {
            if (RunGame.getInput().isPressed(KeyEvent.VK_UP) || RunGame.getInput().isPressed(KeyEvent.VK_W)) {
                moveDistance(0, -25);
                resetMoveTimer();
            }

            if (RunGame.getInput().isPressed(KeyEvent.VK_DOWN) || RunGame.getInput().isPressed(KeyEvent.VK_S)) {
                moveDistance(0, 25);
                resetMoveTimer();
            }

            if (RunGame.getInput().isPressed(KeyEvent.VK_LEFT) || RunGame.getInput().isPressed(KeyEvent.VK_A)) {
                moveDistance(-25, 0);
                resetMoveTimer();
            }

            if (RunGame.getInput().isPressed(KeyEvent.VK_RIGHT) || RunGame.getInput().isPressed(KeyEvent.VK_D)) {
                moveDistance(25, 0);
                resetMoveTimer();
            }

            speed.setLocation(0, 0);
        }

        if (getMoveTimer() > 0) {
            countDownMoveTimer();
        }
    }

}
