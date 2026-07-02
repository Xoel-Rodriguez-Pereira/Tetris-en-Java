package org.xoelrp.tetris.game_panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public static boolean upKeyPress, downKeyPress, leftKeyPress, rightKeyPress, paused;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upKeyPress = true;
        }
        if (code == KeyEvent.VK_S) {
            downKeyPress = true;
        }
        if (code == KeyEvent.VK_A) {
            leftKeyPress = true;
        }
        if (code == KeyEvent.VK_D) {
            rightKeyPress = true;
        }
        if (code == KeyEvent.VK_SPACE && paused) {
            paused = false;
        } else if (code == KeyEvent.VK_SPACE && !paused) {
            paused = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
}
