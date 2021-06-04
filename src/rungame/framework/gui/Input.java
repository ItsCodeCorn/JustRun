package rungame.framework.gui;

import java.util.HashSet;

import javax.swing.JPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter {
    // Singleton
    private static final Input input = new Input();
    private HashSet<Integer> pressed;

    private Input() {
        pressed = new HashSet<>();
    }

    public static boolean isPressed(Integer keyCode) {
        return input.pressed.contains(keyCode);
    }
    public static boolean isReleased(Integer keyCode) {
        return !input.pressed.contains(keyCode);
    }
    public static void listenTo(JPanel panel) {
        panel.addKeyListener(input);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        pressed.add(event.getKeyCode());
    }
    @Override
    public void keyReleased(KeyEvent event) {
        pressed.remove(event.getKeyCode());
    }
}
