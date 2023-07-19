package org.aldomanco.enemy_bullets;

import org.aldomanco.display.Display;
import org.aldomanco.game_screen.BasicBlocks;
import org.aldomanco.game_screen.Player;

import java.awt.*;

/**
 * Created by unieuro on 1/3/2020.
 */
public class EnemyBasicBullet extends EnemyWeaponType {

    private Rectangle bullet;
    private double speed = 2.5d;
    private int xPosition, yPosition;

    public EnemyBasicBullet(double xPosition, double yPosition) {
        bullet = new Rectangle((int) xPosition, (int) yPosition, 5, 5);
        setXPosition((int) xPosition);
        setYPosition((int) yPosition);
    }

    public Rectangle getBullet() {
        return bullet;
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    @Override
    public void draw(Graphics2D graphic) {

        if (bullet == null) {
            return;
        }

        graphic.setColor(Color.ORANGE);
        graphic.fill(bullet);
    }

    @Override
    public void update(double lastLoopTicks, BasicBlocks blocks, Player player) {

        if (bullet == null) {
            return;
        }

        setYPosition((int) (getYPosition() + (lastLoopTicks * speed)));
        bullet.y = getYPosition();

        isOutOfBounds();
        hasCollided(blocks);
    }

    @Override
    public boolean hasCollided(Rectangle player) {

        if (bullet != null && bullet.intersects(player)) {
            return true;
        }

        return false;
    }

    @Override
    protected void hasCollided(BasicBlocks blocks) {

        if (bullet == null) {
            return;
        }

        for (int i = 0; i < blocks.walls.size(); i++) {

            if (bullet.intersects(blocks.walls.get(i))) {
                blocks.walls.remove(i);
                bullet = null;
                break;
            }
        }
    }

    @Override
    public boolean destroy() {
        return false;
    }

    @Override
    protected void isOutOfBounds() {

        if (bullet != null && bullet.y < 0 || bullet.y > Display.HEIGHT - 100 || bullet.x < 0 || bullet.x > Display.WIDTH) {
            bullet = null;
        }
    }
}