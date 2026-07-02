package org.xoelrp.tetris.game_panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class PlayManager {
    // Main Play Area
    final int HEIGHT = 600;
    final int WHIDTH = 300;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    public PlayManager() {
        // Main Play Area Frame
        left_x = 20;
        right_x = left_x + WHIDTH;
        top_y = 20;
        bottom_y = top_y + HEIGHT;
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        //Draw main play area
        int strokeWidth = 4;
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke((float)strokeWidth));
        g2.drawRect(left_x - strokeWidth, top_y, WHIDTH + 2 * strokeWidth, HEIGHT + 2 * strokeWidth);
    }
}
