package org.aldomanco.player_bullets;

import org.aldomanco.display.Display;
import org.aldomanco.game_screen.BasicBlocks;

import java.awt.*;

/**
 * Created by unieuro on 1/4/2020.
 */
public class MachineGun extends PlayerWeaponType {

    private Rectangle bullet;
    private final double speed = 2.5d;

    public MachineGun(double xPosition, double yPosition, int width, int height){

        this.setXPosition(xPosition);
        this.setYPosition(yPosition);
        this.setWidth(width);
        this.setHeight(height);

        this.bullet = new Rectangle((int) getXPosition(), (int) getYPosition(), getWidth(), getHeight());
    }

    @Override
    public void draw(Graphics2D graphics) {

        if (bullet == null){
            return;
        }

        graphics.setColor(Color.YELLOW);
        graphics.fill(bullet);
    }

    @Override
    public void update(double lastLoopTicks, BasicBlocks blocks) {

        if (bullet==null){
            return;
        }

        this.setYPosition(this.getYPosition() - (lastLoopTicks*speed));
        bullet.y = (int) this.getYPosition();

        hasCollided(blocks);
        isOutOfBounds();
    }

    @Override
    public boolean hasCollided(Rectangle rectangle) {

        if (this.bullet == null){
            return false;
        }

        if (bullet.intersects(rectangle)){
            this.bullet = null;
            return true;
        }
        return false;
    }

    @Override
    public boolean hasCollided(Polygon polygon) {
        return false;
    }

    @Override
    public boolean destroy() {

        if (bullet==null){
            return true;
        }
        return false;
    }

    @Override
    protected void hasCollided(BasicBlocks blocks) {

        for (int i = 0; i < blocks.walls.size(); i++) {

            if (bullet.intersects(blocks.walls.get(i))){
                blocks.walls.remove(i);
                bullet=null;
                return;
            }
        }
    }

    @Override
    protected void isOutOfBounds() {

        if (this.bullet == null){
            return;
        }

        if (bullet.y < 0 || bullet.y > Display.HEIGHT || bullet.x < 0 || bullet.x > Display.WIDTH){
            bullet = null;
        }
    }
}