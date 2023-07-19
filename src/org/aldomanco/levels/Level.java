package org.aldomanco.levels;

import org.aldomanco.enemy_types.EnemyType;
import org.aldomanco.enemy_types.EnemyTypeBasic;
import org.aldomanco.game_screen.BasicBlocks;
import org.aldomanco.game_screen.Player;
import org.aldomanco.handler.EnemyBulletHandler;
import org.aldomanco.sound.Sound;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by unieuro on 1/4/2020.
 */
public class Level {

    private Player player;
    private ArrayList<EnemyType> enemies = new ArrayList<>();
    private EnemyBulletHandler bulletHandler;

    private Sound beep, boop;
    private boolean beep_OR_boop;

    private int limitBullet;
    private double speedEnemies;
    private int scoreIncrement;
    private int violence;

    private int currentLevel;
    private int rowsOfEnemies;

    public Level(Player player, EnemyBulletHandler bulletHandler, int limit, double speedEnemies, int scoreIncrement, int violence, int currentLevel, int rowsOfEnemies) {

        this.player = player;
        this.bulletHandler = bulletHandler;

        this.speedEnemies = speedEnemies;
        this.scoreIncrement = scoreIncrement;
        this.violence = violence;

        this.currentLevel = currentLevel;
        this.rowsOfEnemies = rowsOfEnemies;

        addEnemies();

        this.player.setLimitBullet(limit);
        limitBullet = limit;

        beep = new Sound("/org/aldomanco/media/sounds/beep.wav");
        boop = new Sound("/org/aldomanco/media/sounds/boop.wav");
    }

    public void draw(Graphics2D graphics) {

        if (enemies == null) {
            return;
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(graphics);
        }
        bulletHandler.draw(graphics);
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    @SuppressWarnings("all")
    public void update(double lastLoopTicks, BasicBlocks blocks) {

        if (enemies == null) {
            return;
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update(lastLoopTicks, blocks, player);
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).hasCollided(i, blocks, player, enemies);
        }

        bulletHandler.update(lastLoopTicks, blocks, player);
        hasDirectionChanged(lastLoopTicks);
    }

    public void hasDirectionChanged(double lastLoopTicks) {

        if (enemies == null) {
            return;
        }

        for (int i = 0; i < enemies.size(); i++) {

            if (enemies.get(i).isOutOfBounds()) {
                changeDirectionAllEnemies(lastLoopTicks);
            }
        }
    }

    @SuppressWarnings("all")
    public void changeDirectionAllEnemies(double lastLoopTicks) {

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).changeDirection(lastLoopTicks);
        }

        if (beep_OR_boop) {
            beep_OR_boop = false;
            boop.play();
        } else {
            beep_OR_boop = true;
            beep.play();
        }
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    @SuppressWarnings("all")
    public boolean isGameOver() {

        for (int i = 0; i < enemies.size(); i++) {

            EnemyTypeBasic currentEnemy = (EnemyTypeBasic) enemies.get(i);

            if (currentEnemy.getRectangle().y > 390) {
                return true;
            }

            if(player.getLimitBullet()<=0){
                return true;
            }
        }

        return player.getHealth() <= 0;
    }

    public void setScoreIncrement(int scoreIncrement) {
        this.scoreIncrement = scoreIncrement;
    }

    public boolean isComplete() {
        return enemies.isEmpty();
    }

    public void destroy() {

    }

    public void reset() {
        player.reset();
        enemies.clear();
        addEnemies();

        bulletHandler.reset();
    }

    @SuppressWarnings("all")
    public void addEnemies() {

        EnemyType enemy = null;

        for (int y = 0; y < rowsOfEnemies; y++) {

            for (int x = 0; x < 10; x++) {

                if (rowsOfEnemies == 5) {
                    switch (y) {
                        case 0:
                            enemy = new EnemyTypeBasic(150 + (x * 40), 25 + (y * 40), 1, 3, bulletHandler, speedEnemies, scoreIncrement, violence, 1, "/org/aldomanco/media/sprites/Invader1.png");
                            break;
                        case 1:
                            enemy = new EnemyTypeBasic(150 + (x * 40), 25 + (y * 40), 1, 3, bulletHandler, speedEnemies, scoreIncrement, violence, 1, "/org/aldomanco/media/sprites/Invader2.png");
                            break;
                        case 2:
                            enemy = new EnemyTypeBasic(150 + (x * 40), 25 + (y * 40), 1, 3, bulletHandler, speedEnemies, scoreIncrement, violence, 1, "/org/aldomanco/media/sprites/Invader3.png");
                            break;
                        case 3:
                            enemy = new EnemyTypeBasic(150 + (x * 40), 25 + (y * 40), 1, 3, bulletHandler, speedEnemies, scoreIncrement, violence, 1, "/org/aldomanco/media/sprites/Invader4.png");
                            break;
                        case 4:
                            enemy = new EnemyTypeBasic(150 + (x * 40), 25 + (y * 40), 1, 3, bulletHandler, speedEnemies, scoreIncrement, violence, 1, "/org/aldomanco/media/sprites/Invader6.png");
                            break;
                    }
                } else if (rowsOfEnemies == 6) {
                    switch (y) {
                        case 0:
                            enemy = new EnemyTypeBasic(150 + (x * 40), 25 + (y * 40), 1, 3, bulletHandler, speedEnemies, scoreIncrement, violence, 1, "/org/aldomanco/media/sprites/Invader1.png");
                            break;
                        case 1:
                            enemy = new EnemyTypeBasic(150 + (x * 40), 25 + (y * 40), 1, 3, bulletHandler, speedEnemies, scoreIncrement, violence, 1, "/org/aldomanco/media/sprites/Invader2.png");
                            break;
                        case 2:
                            enemy = new EnemyTypeBasic(150 + (x * 40), 25 + (y * 40), 1, 3, bulletHandler, speedEnemies, scoreIncrement, violence, 1, "/org/aldomanco/media/sprites/Invader3.png");
                            break;
                        case 3:
                            enemy = new EnemyTypeBasic(150 + (x * 40), 25 + (y * 40), 1, 3, bulletHandler, speedEnemies, scoreIncrement, violence, 1, "/org/aldomanco/media/sprites/Invader4.png");
                            break;
                        case 4:
                            enemy = new EnemyTypeBasic(150 + (x * 40), 25 + (y * 40), 1, 3, bulletHandler, speedEnemies, scoreIncrement, violence, 1, "/org/aldomanco/media/sprites/Invader5.png");
                            break;
                        case 5:
                            enemy = new EnemyTypeBasic(150 + (x * 40), 25 + (y * 40), 1, 3, bulletHandler, speedEnemies, scoreIncrement, violence, 1, "/org/aldomanco/media/sprites/Invader6.png");
                            break;
                    }
                }
                enemies.add(enemy);
            }
        }
    }
}
