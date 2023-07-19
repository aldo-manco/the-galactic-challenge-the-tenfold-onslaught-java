package org.aldomanco.handler;

import org.aldomanco.enemy_bullets.EnemyWeaponType;
import org.aldomanco.explosion.ExplosionManager;
import org.aldomanco.game_screen.BasicBlocks;
import org.aldomanco.game_screen.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by unieuro on 1/3/2020.
 */
public class EnemyBulletHandler {

    private List<EnemyWeaponType> weaponTypes = new ArrayList<>();

    public void addBullet(EnemyWeaponType weaponType){
        this.weaponTypes.add(weaponType);
    }

    public void draw(Graphics2D graphic){

        for (EnemyWeaponType enemyWeaponType : weaponTypes) {
            enemyWeaponType.draw(graphic);
        }
    }

    public void update(double lastLoopTicks, BasicBlocks blocks, Player player){

        for (int i = 0; i < weaponTypes.size(); i++) {

            weaponTypes.get(i).update(lastLoopTicks, blocks, player);

            if (weaponTypes.get(i).hasCollided(player.getRectangle())){
                ExplosionManager.createPixelExplosion(weaponTypes.get(i).getXPosition(), weaponTypes.get(i).getYPosition());
                weaponTypes.remove(i);
                player.hit();
            }
        }
    }

    public void reset(){
        weaponTypes.clear();
    }
}