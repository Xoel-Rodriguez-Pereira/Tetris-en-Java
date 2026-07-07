package org.xoelrp.tetris.mino;

import java.awt.Color;

import org.xoelrp.tetris.game_panel.GamePanel;

public class Hero extends Tetramino {

    public Hero() {
        create(Color.CYAN);
    }

    @Override
    public void setXY(int x, int y) {
        //     
        // 1 0 2 3
        //
        //
        block[0].x = x;
        block[0].y = y;
        block[1].x = block[0].x - Block.SIZE;
        block[1].y = block[0].y;
        block[2].x = block[0].x + Block.SIZE;
        block[2].y = block[0].y;
        block[3].x = block[0].x + 2*Block.SIZE;
        block[3].y = block[0].y;
    }

    @Override
    public void getDirection1() {
        //     
        // 1 0 2 3
        //
        //
        tempBlock[0].x = block[0].x;
        tempBlock[0].y = block[0].y;
        tempBlock[1].x = block[0].x - Block.SIZE;
        tempBlock[1].y = block[0].y;
        tempBlock[2].x = block[0].x + Block.SIZE;
        tempBlock[2].y = block[0].y;
        tempBlock[3].x = block[0].x + 2*Block.SIZE;
        tempBlock[3].y = block[0].y;

        GamePanel.soundEffect.play(1, false);
        updateXY(1);
    }

    @Override
    public void getDirection2() {
        //   1  
        //   0
        //   2
        //   3
        tempBlock[0].x = block[0].x;
        tempBlock[0].y = block[0].y;
        tempBlock[1].x = block[0].x;
        tempBlock[1].y = block[0].y - Block.SIZE;
        tempBlock[2].x = block[0].x;
        tempBlock[2].y = block[0].y + Block.SIZE;
        tempBlock[3].x = block[0].x;
        tempBlock[3].y = block[0].y + 2*Block.SIZE;

        GamePanel.soundEffect.play(1, false);
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

