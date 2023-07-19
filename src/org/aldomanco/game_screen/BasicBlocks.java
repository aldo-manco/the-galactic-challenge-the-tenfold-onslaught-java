package org.aldomanco.game_screen;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by unieuro on 1/2/2020.
 */
public class BasicBlocks {

    public ArrayList<Rectangle> walls = new ArrayList<Rectangle>();

    public BasicBlocks() {
        basicBlocks(75, 450);
        basicBlocks(275, 450);
        basicBlocks(475, 450);
        basicBlocks(675, 450);
    }

    public void draw(Graphics2D graphic) {

        graphic.setColor(Color.CYAN);

        for (int i = 0; i < walls.size(); i++) {
            graphic.fill(walls.get(i));
        }
    }

    public void basicBlocks(int xPosition, int yPosition) {

        int wallWidth = 3;
        int x = 0, y = 0;

        for (int i = 0; i < 13; i++) {

            if (14 + (i * 2) + wallWidth < 22 + wallWidth) {
                row(14 + (i * 2) + wallWidth, xPosition - (i * 3), yPosition + (i * 3));
                x = (i * 3) + 3;
            } else {
                row(22 + wallWidth, xPosition - x, yPosition + (i * 3));
                y = (i * 3);
            }
        }

        for (int i = 0; i < 5; i++) {
            row(8 + wallWidth - i, xPosition - x, (yPosition + y) + (i * 3));
        }

        for (int i = 0; i < 5; i++) {
            row(8 + wallWidth - i, (xPosition - x + (14 * 3)) + (i * 3), (yPosition + y) + (i * 3));
        }
    }

    public void row(int rows, int xPosition, int yPosition) {

        for (int i = 0; i < rows; i++) {
            Rectangle brick = new Rectangle(xPosition + (i * 3), yPosition, 3, 3);
            walls.add(brick);
        }
    }

    public void reset() {

        walls.clear();

        basicBlocks(75, 450);
        basicBlocks(275, 450);
        basicBlocks(475, 450);
        basicBlocks(675, 450);
    }
}
