package org.aldomanco.game_screen;

import org.aldomanco.display.Display;
import org.aldomanco.timer.Timer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by unieuro on 1/2/2020.
 */
public class Player implements KeyListener {

    private final double speed = 5.0d;
    private int health;

    private BufferedImage sprite;
    private Rectangle rectangle;
    private double xPosition, yPosition, initialXPosition, initialYPosition;
    private int width, height;
    private BasicBlocks blocks;

    private boolean left = false, right = false, shoot = false;

    public PlayerWeapons playerWeapons;

    private int limitBullet;

    public Player(double xPosition, double yPosition, int width, int height, BasicBlocks blocks, int limitBullet) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;

        this.initialXPosition = xPosition;
        this.initialYPosition = yPosition;

        this.width = width;
        this.height = height;

        this.health = 3;

        this.limitBullet = limitBullet;

        rectangle = new Rectangle((int) xPosition, (int) yPosition + 25, width, height - 25);

        try {
            URL url = this.getClass().getResource("/org/aldomanco/media/sprites/player.png");
            sprite = ImageIO.read(url);
        } catch (IOException e) {

        }

        this.blocks = blocks;

        playerWeapons = new PlayerWeapons();
    }

    public void draw(Graphics2D graphic) {
        graphic.drawImage(sprite, (int) xPosition, (int) yPosition, width, height, null);
        playerWeapons.draw(graphic);
    }

    Timer timerp = new Timer();

    public void update(double lastLoopTicks) {

        if (right && !left && xPosition < Display.WIDTH - width) {
            xPosition += speed * lastLoopTicks;
            rectangle.x = (int) xPosition;
        }

        if (left && !right && xPosition > 10) {
            xPosition -= speed * lastLoopTicks;
            rectangle.x = (int) xPosition;
        }

        playerWeapons.update(lastLoopTicks, blocks);

        if (shoot) {
            playerWeapons.shootBullet(xPosition, yPosition, 5, 5);

            if (timerp.timerEvent(350)){
                limitBullet--;
            }
        }
    }


    @Override
    public void keyPressed(KeyEvent keyEvent) {

        int key = keyEvent.getKeyCode();

        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            right = true;
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            left = true;
        }

        if (key == KeyEvent.VK_SPACE) {
            if (limitBullet > 0) {
                shoot = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

        int key = keyEvent.getKeyCode();

        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            right = false;
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            left = false;
        }

        if (key == KeyEvent.VK_SPACE) {
            shoot = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void hit() {
        setHealth(getHealth() - 1);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void reset() {

        health = 3;

        left = false;
        right = false;
        shoot = false;

        xPosition = initialXPosition;
        yPosition = initialYPosition;

        rectangle.x = (int) xPosition;
        rectangle.y = (int) yPosition;

        playerWeapons.reset();
    }

    public int getLimitBullet() {
        return limitBullet;
    }

    public void setLimitBullet(int limitBullet) {
        this.limitBullet = limitBullet;
    }
}
