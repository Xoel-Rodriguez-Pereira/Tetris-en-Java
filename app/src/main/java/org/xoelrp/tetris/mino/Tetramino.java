package org.xoelrp.tetris.mino;

import java.awt.Color;
import java.awt.Graphics2D;

public class Tetramino {
    
    public Block block[] = new Block[4];
    public Block tempBlock[] = new Block[4]; 

    public void create(Color color) {
        block[0] = new Block(color);
        block[1] = new Block(color);
        block[2] = new Block(color);
        block[3] = new Block(color);
        tempBlock[0] = new Block(color);
        tempBlock[1] = new Block(color);
        tempBlock[2] = new Block(color);
        tempBlock[3] = new Block(color);
    }
    public void setXY(int x, int y) {}
    public void updateXY(int direction) {}
    public void update() {

    }
    public void draw(Graphics2D g2) {
        g2.setColor(block[0].color);
        g2.fillRect(block[0].x, block[0].y, Block.SIZE, Block.SIZE);
        g2.fillRect(block[1].x, block[1].y, Block.SIZE, Block.SIZE);
        g2.fillRect(block[2].x, block[2].y, Block.SIZE, Block.SIZE);
        g2.fillRect(block[3].x, block[3].y, Block.SIZE, Block.SIZE);
    }

}
