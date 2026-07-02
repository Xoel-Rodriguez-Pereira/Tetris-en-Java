package org.xoelrp.tetris.game_panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Random;

import org.xoelrp.tetris.mino.Block;
import org.xoelrp.tetris.mino.BlueRicky;
import org.xoelrp.tetris.mino.ClevelandZ;
import org.xoelrp.tetris.mino.Hero;
import org.xoelrp.tetris.mino.OrangeRicky;
import org.xoelrp.tetris.mino.RhodeIlandZ;
import org.xoelrp.tetris.mino.Smashboy;
import org.xoelrp.tetris.mino.Teewee;
import org.xoelrp.tetris.mino.Tetramino;

public class PlayManager {
    // Main Play Area
    final int HEIGHT = 600;
    final int WIDTH = 300;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    //Tetramino
    Tetramino currentTetramino;
    final int TETRAMINO_START_X;
    final int TETRAMINO_START_Y;
    Tetramino nextTetramino;
    final int NEXT_TETRAMINO_X;
    final int NEXT_TETRAMINO_Y;
    public static ArrayList<Block> staticBlocks = new ArrayList<>();

    // Other logic
    public static int dropInterval = 60;  
    boolean gameOver;

    public PlayManager() {
        // Main Play Area Frame
        left_x = 20;
        right_x = left_x + WIDTH;
        top_y = 20;
        bottom_y = top_y + HEIGHT;

        // Tetramino
        TETRAMINO_START_X = left_x + (WIDTH/2) - Block.SIZE;
        TETRAMINO_START_Y = top_y + Block.SIZE;

        NEXT_TETRAMINO_X = right_x + 10 + 3*Block.SIZE;
        NEXT_TETRAMINO_Y = bottom_y - 3*Block.SIZE;

        // Set start tetramino
        currentTetramino = pickTetramino();
        currentTetramino.setXY(TETRAMINO_START_X, TETRAMINO_START_Y);
        nextTetramino = pickTetramino();
        nextTetramino.setXY(NEXT_TETRAMINO_X, NEXT_TETRAMINO_Y);

        gameOver = false;
    }

    private Tetramino pickTetramino() {
        Tetramino tetramino = null;
        int i = new Random().nextInt(7);
        switch (i) {
            case 0 -> tetramino = new BlueRicky();
            case 1 -> tetramino = new OrangeRicky();
            case 2 -> tetramino = new ClevelandZ();
            case 3 -> tetramino = new RhodeIlandZ();
            case 4 -> tetramino = new Hero();
            case 5 -> tetramino = new Smashboy();
            case 6 -> tetramino = new Teewee();
            default -> throw new AssertionError();
        }
        return tetramino;
    }

    public void checkDelete() {
        int x = left_x;
        int y = top_y;
        int blockCount = 0;

        while (x < right_x && y < bottom_y) {
            
            for (Block staticB : staticBlocks) {
                if (staticB.x == x && staticB.y == y) {
                    blockCount++;
                }
            }

            x += Block.SIZE;

            if (x == right_x) {
                if (blockCount == 10) {
                    for (int i = staticBlocks.size() -1; i > -1; i--) {
                        if (staticBlocks.get(i).y == y) {
                            staticBlocks.remove(i);
                        }
                    }
                    // Move all the static block above the deleted row
                    for (Block b : staticBlocks) {
                        if (b.y < y) {
                            b.y += Block.SIZE;
                        }
                    }
                }
                x = left_x;
                y += Block.SIZE;
                blockCount = 0;
            }
        }
    }

    public void checkGameOver() {
        for (Block b : staticBlocks) {
            if (b.x == TETRAMINO_START_X && b.y == TETRAMINO_START_Y) {
                gameOver = true;
            }
        }
    }

    public void update() {
        
        if (!KeyHandler.paused) {
            if (!currentTetramino.active && !gameOver) {
                // Store current tetramino as static blocks
                staticBlocks.add(currentTetramino.block[0]);
                staticBlocks.add(currentTetramino.block[1]);
                staticBlocks.add(currentTetramino.block[2]);
                staticBlocks.add(currentTetramino.block[3]);

                // Select next tetramino
                currentTetramino = nextTetramino;
                currentTetramino.setXY(TETRAMINO_START_X, TETRAMINO_START_Y);
                nextTetramino = pickTetramino();
                nextTetramino.setXY(NEXT_TETRAMINO_X, NEXT_TETRAMINO_Y);

                // Check game over
                checkGameOver();

                // When a tetramino becames inactive, check if the row can be deleted
                checkDelete();

            } else {
                if (!gameOver) {
                    currentTetramino.update();
                } 
            }
        } 
    }

    public void draw(Graphics2D g2) {
        //Draw main play area
        int strokeWidth = 4;
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke((float)strokeWidth));
        g2.drawRect(left_x - strokeWidth, top_y - strokeWidth, WIDTH + 2 * strokeWidth, HEIGHT + 2 * strokeWidth);

        //Draw next tetramino area
        int x = right_x + 40;
        int y = bottom_y - 180;
        g2.drawRect(x, y, 180, 180);
        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT", x+50, y+40);

        //Draw score area
        x = right_x + 40;
        y = top_y;
        g2.drawRect(x, y, 180, 380);
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("Score:", x+15, y+50);
        g2.drawString("Level:", x+15, y+100);

        // Draw currentTetramino
        if (currentTetramino != null) {
            currentTetramino.draw(g2);
        }

        // Draw nextTetramino
        if (nextTetramino != null) {
            nextTetramino.draw(g2);
        }

        // Draw static blocks
        for (Block b : staticBlocks) {
            b.Draw(g2);
        }

        // Draw paused
        if (KeyHandler.paused && !gameOver) {
            g2.setColor(Color.YELLOW);
            g2.setFont(new Font("Arial", Font.BOLD, 50));
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            x = left_x + 45;
            y = top_y + 270;
            g2.drawString("PAUSED", x, y);
        }

        // Draw gameOver
        if (gameOver) {
            g2.setFont(new Font("NotoSANS", Font.BOLD | Font.ITALIC, 70));
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            x = left_x + 45;
            y = top_y + 300;
            
            // Dibujar sombra negra
            g2.setColor(Color.BLACK);
            g2.drawString("GAME OVER", x + 4, y + 4);
            
            // Dibujar texto rojo
            g2.setColor(Color.RED);
            g2.drawString("GAME OVER", x, y);
        }
    }
}
