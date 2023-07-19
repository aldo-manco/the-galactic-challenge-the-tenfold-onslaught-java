package org.aldomanco.enemy_types;

import org.aldomanco.game_screen.BasicBlocks;
import org.aldomanco.game_screen.Player;
import org.aldomanco.handler.EnemyBulletHandler;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by unieuro on 1/3/2020.
 */
public abstract class EnemyType {

    private EnemyBulletHandler bulletHandler;

    public EnemyType(EnemyBulletHandler bulletHandler){
        this.bulletHandler = bulletHandler;
    }

    public abstract void draw(Graphics2D graphic);
    public abstract void update(double lastLoopTicks, BasicBlocks blocks, Player player);
    public abstract void changeDirection(double lastLoopTicks);

    public abstract boolean deathScene();
    public abstract boolean hasCollided(int i, BasicBlocks blocks, Player player, ArrayList<EnemyType> enemies);
    public abstract boolean isOutOfBounds();

    public EnemyBulletHandler getBulletHandler() {
        return bulletHandler;
    }
}
