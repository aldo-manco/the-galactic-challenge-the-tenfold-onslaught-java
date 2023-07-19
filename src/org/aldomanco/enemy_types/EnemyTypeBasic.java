package org.aldomanco.enemy_types;

import org.aldomanco.display.Display;
import org.aldomanco.enemy_bullets.EnemyBasicBullet;
import org.aldomanco.game_screen.BasicBlocks;
import org.aldomanco.game_screen.SinglePlayerScreen;
import org.aldomanco.game_screen.MultiPlayerScreen;
import org.aldomanco.game_screen.Player;
import org.aldomanco.handler.EnemyBulletHandler;
import org.aldomanco.sound.Sound;
import org.aldomanco.sprite.SpriteAnimation;
import org.aldomanco.timer.Timer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by unieuro on 1/3/2020.
 */
public class EnemyTypeBasic extends EnemyType {

    private double speed = 1.0d;

    private Rectangle rectangle;
    private SpriteAnimation spriteEnemy;

    private int shootTime;
    private Timer shootTimer;

    private Sound explosionSound;

    private int scoreIncrement;
    private int violence;

    private int stateMachine;

    public EnemyTypeBasic(double xPosition, double yPosition, int rows, int columns, EnemyBulletHandler bulletHandler, double speed, int scoreIncrement, int violence, int stateMachine, String imagePath) {

        super(bulletHandler);

        this.speed = speed;
        this.scoreIncrement = scoreIncrement;
        this.violence = violence;

        this.stateMachine = stateMachine;

        spriteEnemy = new SpriteAnimation(xPosition, yPosition, rows, columns, 300, imagePath);
        spriteEnemy.setWidth(25);
        spriteEnemy.setHeight(25);
        spriteEnemy.setLimit(2);

        this.setRectangle(new Rectangle((int) spriteEnemy.getXPosition(), (int) spriteEnemy.getYPosition(), spriteEnemy.getWidth(), spriteEnemy.getHeight()));
        spriteEnemy.setLoop(true);

        shootTimer = new Timer();
        shootTime = new Random().nextInt(violence);

        explosionSound = new Sound("/org/aldomanco/media/sounds/explosion.wav");
    }

    @Override
    public void draw(Graphics2D graphic) {
        spriteEnemy.draw(graphic);
    }

    @Override
    public void update(double lastLoopTicks, BasicBlocks blocks, Player player) {

        spriteEnemy.update(lastLoopTicks);

        spriteEnemy.setXPosition(spriteEnemy.getXPosition() - (lastLoopTicks * speed));
        this.getRectangle().x = (int) spriteEnemy.getXPosition();

        if (shootTimer.timerEvent(shootTime)) {
            getBulletHandler().addBullet(new EnemyBasicBullet(getRectangle().x, getRectangle().y));
            shootTime = new Random().nextInt(violence);
        }
    }

    @Override
    public void changeDirection(double lastLoopTicks) {

        speed *= -1.15d;

        spriteEnemy.setXPosition(spriteEnemy.getXPosition() - (lastLoopTicks * speed));
        this.getRectangle().x = (int) spriteEnemy.getXPosition();

        spriteEnemy.setYPosition(spriteEnemy.getYPosition() + (lastLoopTicks * 15));
        this.getRectangle().y = (int) spriteEnemy.getYPosition();
    }

    @Override
    public boolean deathScene() {

        if (!spriteEnemy.isPlay()) {
            return false;
        }

        if (spriteEnemy.isSpriteAnimationDestroyed()) {
            if (!explosionSound.isPlaying()) {
                explosionSound.play();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean hasCollided(int i, BasicBlocks blocks, Player player, ArrayList<EnemyType> enemies) {

        if (spriteEnemy.isPlay()) {
            if (enemies.get(i).deathScene()) {
                enemies.remove(i);
            }
            return false;
        }

        for (int j = 0; j < player.playerWeapons.weapons.size(); j++) {
            if (enemies != null && player.playerWeapons.weapons.get(j).hasCollided(((EnemyTypeBasic) enemies.get(i)).getRectangle())) {
                spriteEnemy.resetLimit();
                spriteEnemy.setAnimationSpeed(120);
                spriteEnemy.setPlay(true, true);

                if (stateMachine==1){
                    SinglePlayerScreen.SCORE += scoreIncrement;
                }else if (stateMachine==2 && MultiPlayerScreen.whichPlayer==1){
                    MultiPlayerScreen.firstPlayer_SCORE += scoreIncrement;
                }else if(stateMachine==2 && MultiPlayerScreen.whichPlayer==2){
                    MultiPlayerScreen.secondPlayer_SCORE += scoreIncrement;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isOutOfBounds() {

        if (rectangle.x > 0 && rectangle.x < Display.WIDTH - rectangle.width) {
            return false;
        }
        return true;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
