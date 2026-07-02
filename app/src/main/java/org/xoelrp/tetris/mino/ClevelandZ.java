package org.xoelrp.tetris.mino;

import java.awt.Color;

public class ClevelandZ extends Tetramino {

    public ClevelandZ() {
        create(Color.GREEN);
    }

    public void setXY(int x, int y) {
        // 3 2   
        //   0 1
        //
        block[0].x = x;
        block[0].y = y;
        block[1].x = block[0].x + Block.SIZE;
        block[1].y = block[0].y;
        block[2].x = block[0].x;
        block[2].y = block[0].y - Block.SIZE;
        block[3].x = block[0].x - Block.SIZE;
        block[3].y = block[0].y - Block.SIZE;
    }

    @Override
    public void getDirection1() {
        // 3 2
        //   0 1
        //    
        tempBlock[0].x = block[0].x;
        tempBlock[0].y = block[0].y;
        tempBlock[1].x = block[0].x + Block.SIZE;
        tempBlock[1].y = block[0].y;
        tempBlock[2].x = block[0].x;
        tempBlock[2].y = block[0].y - Block.SIZE;
        tempBlock[3].x = block[0].x - Block.SIZE;
        tempBlock[3].y = block[0].y - Block.SIZE;

        updateXY(1);
    }

    @Override
    public void getDirection2() {
        //     3
        //   0 2
        //   1
        tempBlock[0].x = block[0].x;
        tempBlock[0].y = block[0].y;
        tempBlock[1].x = block[0].x;
        tempBlock[1].y = block[0].y + Block.SIZE;
        tempBlock[2].x = block[0].x + Block.SIZE;
        tempBlock[2].y = block[0].y;
        tempBlock[3].x = block[0].x + Block.SIZE;
        tempBlock[3].y = block[0].y - Block.SIZE;

        updateXY(2);
    }

    @Override
    public void getDirection3() {
        getDirection1();
    }

    @Override
    public void getDirection4() {
        getDirection2();
    }
}