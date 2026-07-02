package org.xoelrp.tetris.game_panel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    final static private Integer HEIGHT = 640;
    final static private Integer WHIDTH = 560;

    final private Integer objetiveFPS = 60;
    Thread gameThread;

    public GamePanel() {

        //GamePanel settings
        this.setPreferredSize(new Dimension(WHIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(null);

    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
