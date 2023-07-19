package org.aldomanco.game_screen;

import org.aldomanco.explosion.ExplosionManager;
import org.aldomanco.player_bullets.MachineGun;
import org.aldomanco.player_bullets.PlayerWeaponType;
import org.aldomanco.sound.Sound;
import org.aldomanco.timer.Timer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by unieuro on 1/2/2020.
 */
public class PlayerWeapons {

    private Timer timer;
    private ExplosionManager explosionManager;
    public ArrayList<PlayerWeaponType> weapons = new ArrayList<PlayerWeaponType>();
    private Sound soundShoot;

    public PlayerWeapons() {
        explosionManager = new ExplosionManager();
        timer = new Timer();
        soundShoot = new Sound("/org/aldomanco/media/sounds/shoot.wav");
    }

    public void draw(Graphics2D graphic) {

        explosionManager.draw(graphic);

        for (int i = 0; i < weapons.size(); i++) {
            weapons.get(i).draw(graphic);
        }
    }

    public void update(double lastLoopTicks, BasicBlocks blocks) {

        explosionManager.update(lastLoopTicks);

        for (int i = 0; i < weapons.size(); i++) {

            weapons.get(i).update(lastLoopTicks, blocks);

            if (weapons.get(i).destroy()) {
                ExplosionManager.createPixelExplosion(weapons.get(i).getXPosition(), weapons.get(i).getYPosition());
                weapons.remove(i);
            }
        }
    }

    public void shootBullet(double xPosition, double yPosition, int width, int height) {

        if (timer.timerEvent(350)) {

            if (soundShoot.isPlaying()) {
                soundShoot.stop();
            }

            soundShoot.play();

            weapons.add(new MachineGun(xPosition + 22, yPosition + 15, width, height));
        }
    }

    public void reset() {
        weapons.clear();
    }
}
