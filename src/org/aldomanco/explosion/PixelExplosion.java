package org.aldomanco.explosion;

import java.awt.*;
import java.util.Random;

/**
 * Created by unieuro on 1/4/2020.
 */
public class PixelExplosion implements ExplosionType {

    private double[] xPositions, yPositions;
    private double[] xPositionsVolume, yPositionsVolume;
    private double[] angle, energy;

    public PixelExplosion(double xPosition, double yPosition){

        int index = 12;

        this.xPositions = new double[index];
        this.yPositions = new double[index];
        this.xPositionsVolume = new double[index];
        this.yPositionsVolume = new double[index];
        this.angle = new double[index];
        this.energy = new double[index];

        for (int i = 0; i < index; i++) {

            this.xPositions[i] = xPosition;
            this.yPositions[i] = yPosition;

            this.xPositionsVolume[i] = Math.random()*1;
            this.yPositionsVolume[i] = Math.random()*1;
            this.energy[i] = Math.random();

            angle[i] = new Random().nextInt(6)+1;
        }
    }

    @Override
    public void draw(Graphics2D graphics) {

        for (int i = 0; i < xPositions.length; i++) {

            if (energy[i]>= 0.0d){
                graphics.setColor(new Color(1.0f, 1.0f, 0f, (float) energy[i]));
            } else{
                graphics.setColor(new Color(1.0f, 1.0f, 0f, 0));
            }
            graphics.fillRect((int) xPositions[i], (int) yPositions[i], 3, 3);
        }
    }

    @Override
    public void update(double lastLoopTicks) {

        for (int i = 0; i < xPositions.length; i++) {

            energy[i] -= 0.01d;
            xPositions[i] += xPositionsVolume[i] * Math.cos(angle[i]);
            yPositions[i] += yPositionsVolume[i] * Math.cos(angle[i]);
        }
    }

    @Override
    public boolean destroy() {

        int destroy = 0;

        for (int i = 0; i < xPositions.length; i++) {

            if (energy[i]<0.0d){
                destroy++;
            }else {
                break;
            }
        }

        if (destroy==energy.length){
            return true;
        }
        return false;
    }
}
