package org.xoelrp.tetris.mino;

import java.awt.Color;

public class OrangeRicky extends Tetramino {

    public OrangeRicky() {
        create(Color.ORANGE);
    }

    public void setXY(int x, int y) {
        //
        //     3
        // 1 0 2
        block[0].x = x;
        block[0].y = y;
        block[1].x = block[0].x - Block.SIZE;
        block[1].y = block[0].y;
        block[2].x = block[0].x + Block.SIZE;
        block[2].y = block[0].y;
        block[3].x = block[0].x + Block.SIZE;
        block[3].y = block[0].y - Block.SIZE;
    }
}