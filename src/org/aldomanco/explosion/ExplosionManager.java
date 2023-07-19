package org.aldomanco.explosion;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by unieuro on 1/4/2020.
 */
public class ExplosionManager {

    private static ArrayList<ExplosionType> explosions = new ArrayList<>();

    public void draw(Graphics2D graphics){

        for (int i = 0; i < explosions.size(); i++) {
            explosions.get(i).draw(graphics);
        }
    }

    public void update(double lastLoopTicks){

        for (int i = 0; i < explosions.size(); i++) {

            explosions.get(i).update(lastLoopTicks);

            if (explosions.get(i).destroy()){
                explosions.remove(i);
            }
        }
    }

    public static void createPixelExplosion(double xPosition, double yPosition){
        ExplosionType explosionType = new PixelExplosion(xPosition, yPosition);
        explosions.add(explosionType);
    }
}
