package org.xoelrp.tetris.game_panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public static boolean upKeyPress, downKeyPress, leftKeyPress, rightKeyPress, paused, retry, mute;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upKeyPress = true;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN || code == KeyEvent.VK_SPACE) {
            downKeyPress = true;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftKeyPress = true;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightKeyPress = true;
        }
        if (code == KeyEvent.VK_R && PlayManager.gameOver) {
            retry = true;
        }
        if (code == KeyEvent.VK_ESCAPE && paused) {
            GamePanel.soundEffect.play(9, false);
            paused = false;
        } else if (code == KeyEvent.VK_ESCAPE && !paused) {
            GamePanel.soundEffect.play(8, false);
            paused = true;
        }
        if (code == KeyEvent.VK_M && mute) {
            GamePanel.music.play(4, true);
            GamePanel.music.loop();
            mute = false;
        } else if (code == KeyEvent.VK_M && !mute) {
            GamePanel.music.stop();
            mute = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
}
