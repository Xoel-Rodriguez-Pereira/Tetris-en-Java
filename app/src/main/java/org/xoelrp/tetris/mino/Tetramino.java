package org.xoelrp.tetris.mino;

import java.awt.Color;
import java.awt.Graphics2D;

import org.xoelrp.tetris.game_panel.GamePanel;
import org.xoelrp.tetris.game_panel.KeyHandler;
import org.xoelrp.tetris.game_panel.PlayManager;

public class Tetramino {
    
    public Block block[] = new Block[4];
    public Block tempBlock[] = new Block[4]; 

    public int autoDropCounter = 0;
    public int direction = 1;

    boolean leftColision, rightColision, bottomColision, deactivating;
    public boolean active = true;
    int deativationCounter = 0;


    public void create(Color color) {
        block[0] = new Block(color);
        block[1] = new Block(color);
        block[2] = new Block(color);
        block[3] = new Block(color);
        tempBlock[0] = new Block(color);
        tempBlock[1] = new Block(color);
        tempBlock[2] = new Block(color);
        tempBlock[3] = new Block(color);

        deactivating = false;
    }
    public void setXY(int x, int y) {}
    public void updateXY(int direction) {
        checkRotationColision();

        if (!leftColision && !rightColision && !bottomColision) {
            this.direction = direction;
            block[0].x = tempBlock[0].x;
            block[0].y = tempBlock[0].y;
            block[1].x = tempBlock[1].x;
            block[1].y = tempBlock[1].y;
            block[2].x = tempBlock[2].x;
            block[2].y = tempBlock[2].y;
            block[3].x = tempBlock[3].x;
            block[3].y = tempBlock[3].y;
        }
    }
    public void getDirection1() {};
    public void getDirection2() {};
    public void getDirection3() {};
    public void getDirection4() {};
    public void deactivate() {
        deativationCounter++;
        if (deativationCounter == 45) {
            checkMovementColision();
            if (bottomColision) {
                active = false;
            }
            deativationCounter = 0;
        }
    }
    public void checkMovementColision() {

        leftColision = false;
        rightColision = false;
        bottomColision = false;

        checkStatickBlockColision();

        // Check frame colision
        // Left
        for (Block b : block) {
            if (b.x == PlayManager.left_x) {
                leftColision = true;
                KeyHandler.leftKeyPress = false;
            }
        }

        // Right
        for (Block b : block) {
            if (b.x + Block.SIZE == PlayManager.right_x) {
                rightColision = true;
                KeyHandler.rightKeyPress = false;
            }
        }

        // Bottom
        for (Block b : block) {
            if (b.y + Block.SIZE == PlayManager.bottom_y) {
                bottomColision = true;
            }
        }
    };
    public void checkRotationColision() {

        leftColision = false;
        rightColision = false;
        bottomColision = false;

        checkStatickBlockColision();

        // Check frame colision
        // Left
        for (Block b : tempBlock) {
            if (b.x < PlayManager.left_x) {
                leftColision = true;
            }
        }

        // Right
        for (Block b : tempBlock) {
            if (b.x + Block.SIZE > PlayManager.right_x) {
                rightColision = true;
            }
        }

        // Bottom
        for (Block b : tempBlock) {
            if (b.y + Block.SIZE > PlayManager.bottom_y) {
                bottomColision = true;
            }
        }
    };

    public void checkStatickBlockColision() {
        for (Block staticBlock : PlayManager.staticBlocks) {

            int targetX = staticBlock.x;
            int targetY = staticBlock.y;

            for (Block b : block) {
                // Bottom
                if(b.y + Block.SIZE == targetY && b.x == targetX) {
                    bottomColision = true;
                }
                // Left
                if(b.x - Block.SIZE == targetX && b.y == targetY) {
                    leftColision = true;
                }
                // Right
                if(b.x + Block.SIZE == targetX && b.y == targetY) {
                    rightColision = true;
                }
            }
        }
     
    }

    public void update() {

        if (deactivating) {
            deactivate();
        }

        if (KeyHandler.upKeyPress) {
            switch (direction) {
                case 1 -> getDirection2();
                case 2 -> getDirection3();
                case 3 -> getDirection4();
                case 4 -> getDirection1();
                default -> throw new AssertionError();
            }
            KeyHandler.upKeyPress = false;
        }

        checkMovementColision();

        if (KeyHandler.downKeyPress) {
            if (!bottomColision) {
                block[0].y += Block.SIZE;
                block[1].y += Block.SIZE;
                block[2].y += Block.SIZE;
                block[3].y += Block.SIZE;
                autoDropCounter = 0;
                KeyHandler.downKeyPress = false;
            }
        }
        if (KeyHandler.leftKeyPress) {
            if (!leftColision) {
                block[0].x -= Block.SIZE;
                block[1].x -= Block.SIZE;
                block[2].x -= Block.SIZE;
                block[3].x -= Block.SIZE;
                KeyHandler.leftKeyPress = false;
            }
        }
        if (KeyHandler.rightKeyPress) {
            if (!rightColision) {
                block[0].x += Block.SIZE;
                block[1].x += Block.SIZE;
                block[2].x += Block.SIZE;
                block[3].x += Block.SIZE;
                KeyHandler.rightKeyPress = false;
            }
        }

        if (bottomColision) {
            if (!deactivating) {
                GamePanel.soundEffect.play(0, false);
            }
            deactivating = true;
        } else {
            autoDropCounter++;
            if (autoDropCounter == PlayManager.dropInterval) {
                block[0].y += Block.SIZE;
                block[1].y += Block.SIZE;
                block[2].y += Block.SIZE;
                block[3].y += Block.SIZE;
                autoDropCounter = 0;
            }
        }
        
    }
    public void draw(Graphics2D g2) {

        int margin = 2;
        g2.setColor(block[0].color);
        g2.fillRect(block[0].x + margin, block[0].y + margin, Block.SIZE - (margin*2), Block.SIZE - (margin*2));
        g2.fillRect(block[1].x + margin, block[1].y + margin, Block.SIZE - (margin*2), Block.SIZE - (margin*2));
        g2.fillRect(block[2].x + margin, block[2].y + margin, Block.SIZE - (margin*2), Block.SIZE - (margin*2));
        g2.fillRect(block[3].x + margin, block[3].y + margin, Block.SIZE - (margin*2), Block.SIZE - (margin*2));
    }

}
