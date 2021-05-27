package rungame;

import java.util.HashSet;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter {
    private HashSet<Integer> pressed;

    public Input() {
        pressed = new HashSet<>();
    }

    public boolean isPressed(Integer keyCode) {
        return pressed.contains(keyCode);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        //System.out.print(event.getKeyChar());
        pressed.add(event.getKeyCode());
    }
    @Override
    public void keyReleased(KeyEvent event) {
        pressed.remove(event.getKeyCode());
    }

}
