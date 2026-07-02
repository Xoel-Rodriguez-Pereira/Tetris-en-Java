package org.xoelrp.tetris.game_panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import org.xoelrp.tetris.mino.Block;
import org.xoelrp.tetris.mino.OrangeRicky;
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

    // Other logic
    public static int dropInterval = 60;  

    public PlayManager() {
        // Main Play Area Frame
        left_x = 20;
        right_x = left_x + WIDTH;
        top_y = 20;
        bottom_y = top_y + HEIGHT;

        // Tetramino
        TETRAMINO_START_X = left_x + (WIDTH/2) - Block.SIZE;
        TETRAMINO_START_Y = top_y + Block.SIZE;

        // Set start tetramino
        currentTetramino = new OrangeRicky();
        currentTetramino.setXY(TETRAMINO_START_X, TETRAMINO_START_Y);
    }

    public void update() {

        currentTetramino.update();
    }

    public void draw(Graphics2D g2) {
        //Draw main play area
        int strokeWidth = 4;
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke((float)strokeWidth));
        g2.drawRect(left_x - strokeWidth, top_y, WIDTH + 2 * strokeWidth, HEIGHT + 2 * strokeWidth);

        //Draw new tetramino area
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
    }
}
