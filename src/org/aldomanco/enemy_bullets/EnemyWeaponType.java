package org.aldomanco.enemy_bullets;

import org.aldomanco.game_screen.BasicBlocks;
import org.aldomanco.game_screen.Player;

import java.awt.*;

/**
 * Created by unieuro on 1/3/2020.
 */
public abstract class EnemyWeaponType {

    public abstract void draw(Graphics2D graphic);
    public abstract void update(double lastLoopTicks, BasicBlocks blocks, Player player);

    public abstract boolean hasCollided(Rectangle rectangle);
    protected abstract void hasCollided(BasicBlocks blocks);

    public abstract boolean destroy();
    protected abstract void isOutOfBounds();

    public abstract int getXPosition();
    public abstract int getYPosition();
}