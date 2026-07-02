package org.xoelrp.tetris.mino;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block extends Rectangle {
    public int x, y;
    public final static int SIZE = 30;
    public Color color;
    private int MARGIN = 2;

    public Block(Color color) {
        this.color = color;
    }

    public void Draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRect(x + MARGIN, y + MARGIN, SIZE - 2 * MARGIN, SIZE - 2 * MARGIN);
    }
}
