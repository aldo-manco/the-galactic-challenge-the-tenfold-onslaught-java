package org.aldomanco.player_bullets;

import org.aldomanco.game_screen.BasicBlocks;

import java.awt.*;

/**
 * Created by unieuro on 1/4/2020.
 */
public abstract class PlayerWeaponType {

    protected double xPosition, yPosition;
    protected int width, height;

    public abstract void draw(Graphics2D graphics);
    public abstract void update(double lastLoopTicks, BasicBlocks blocks);

    public abstract boolean hasCollided(Rectangle rectangle);
    public abstract boolean hasCollided(Polygon polygon);

    public abstract boolean destroy();
    protected abstract void hasCollided(BasicBlocks blocks);
    protected abstract void isOutOfBounds();

    public double getXPosition() {
        return xPosition;
    }

    public void setXPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }

    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}