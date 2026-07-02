package org.xoelrp.tetris.mino;

import java.awt.Color;

public class Smashboy extends Tetramino {

    public Smashboy() {
        create(Color.YELLOW);
    }

    @Override
    public void setXY(int x, int y) {
        // 2 3   
        // 1 0 
        //
        block[0].x = x;
        block[0].y = y;
        block[1].x = block[0].x - Block.SIZE;
        block[1].y = block[0].y;
        block[2].x = block[0].x - Block.SIZE;
        block[2].y = block[0].y - Block.SIZE;
        block[3].x = block[0].x;
        block[3].y = block[0].y - Block.SIZE;
    }

    @Override
    public void getDirection1() {
        // 2 3   
        // 1 0 
        //
        tempBlock[0].x = block[0].x;
        tempBlock[0].y = block[0].y;
        tempBlock[1].x = block[0].x - Block.SIZE;
        tempBlock[1].y = block[0].y;
        tempBlock[2].x = block[0].x - Block.SIZE;
        tempBlock[2].y = block[0].y - Block.SIZE;
        tempBlock[3].x = block[0].x;
        tempBlock[3].y = block[0].y - Block.SIZE;

        updateXY(1);
    }

    @Override
    public void getDirection2() {
        getDirection1();
    }

    @Override
    public void getDirection3() {
        getDirection1();
    }

    @Override
    public void getDirection4() {
        getDirection1();
    }
}