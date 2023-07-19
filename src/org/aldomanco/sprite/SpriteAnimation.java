package org.aldomanco.sprite;

// Support - Sprite Animation ---

import org.aldomanco.timer.Timer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by unieuro on 1/3/2020.
 */
public class SpriteAnimation {

    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private byte currentSprite;

    private boolean loop = false;
    private boolean play = false;
    private boolean destroyAfterAnimation = false;

    private Timer timer;
    private int animationSpeed;
    private double xPosition, yPosition;
    private int width, height;
    private int limit;

    public SpriteAnimation(double xPosition, double yPosition, int rows, int columns, int animationSpeed, String imagePath) {

        this.animationSpeed = animationSpeed;
        this.xPosition = xPosition;
        this.yPosition = yPosition;

        try {
            URL url = this.getClass().getResource(imagePath);
            BufferedImage sprite = ImageIO.read(url);
            int spriteWidth = sprite.getWidth() / columns;
            int spriteHeight = sprite.getHeight() / rows;

            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < columns; x++) {
                    addSprite(sprite, 0 + (x * spriteWidth), 0 + (y * spriteHeight), spriteWidth, spriteHeight);
                }
            }

        } catch (IOException e) {

        }

        timer = new Timer();
        limit = sprites.size() - 1;
    }

    public void draw(Graphics2D graphic) {

        if (isSpriteAnimationDestroyed()) {
            return;
        }

        graphic.drawImage(sprites.get(currentSprite), (int) getXPosition(), (int) getYPosition(), width, height, null);
    }

    public void update(double lastLoopTicks) {

        if (isSpriteAnimationDestroyed()) {
            return;
        }

        if (loop && !play) loopAnimation();
        if (play && !loop) playAnimation();
    }

    public void stopAnimation() {
        loop = false;
        play = false;
    }

    public void resetSprite() {
        loop = false;
        play = false;
        currentSprite = 0;
    }

    private void loopAnimation() {

        if (timer.isTimerReady(animationSpeed) && currentSprite == limit) {
            currentSprite = 0;
            timer.resetTimer();
        } else if (timer.timerEvent(animationSpeed) && currentSprite != limit) {
            currentSprite++;
        }
    }

    private void playAnimation() {

        if (timer.isTimerReady(animationSpeed) && currentSprite != limit && !isDestroyedAfterAnimation()) {
            play = false;
            currentSprite = 0;
        } else if (timer.isTimerReady(animationSpeed) && currentSprite == limit && isDestroyedAfterAnimation()) {
            sprites = null;
        } else if (timer.timerEvent(animationSpeed) && currentSprite != limit) {
            currentSprite++;
        }
    }

    public boolean isSpriteAnimationDestroyed() {

        if (sprites == null) {
            return true;
        }
        return false;
    }

    public void addSprite(BufferedImage spriteMap, int xPosition, int yPosition, int width, int height) {
        sprites.add(spriteMap.getSubimage(xPosition, yPosition, width, height));
    }

    public byte getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(byte currentSprite) {
        this.currentSprite = currentSprite;
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play, boolean destroyAfterAnimation) {

        if (loop) {
            loop = false;
        }

        this.play = play;
        this.setDestroyAfterAnimation(destroyAfterAnimation);
    }

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

    public boolean isDestroyedAfterAnimation() {
        return destroyAfterAnimation;
    }

    public void setDestroyAfterAnimation(boolean destroyAfterAnimation) {
        this.destroyAfterAnimation = destroyAfterAnimation;
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

    public int getAnimationSpeed() {
        return animationSpeed;
    }

    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = (limit > 0) ? limit - 1 : limit;
    }

    public void resetLimit() {
        limit = sprites.size() - 1;
    }
}