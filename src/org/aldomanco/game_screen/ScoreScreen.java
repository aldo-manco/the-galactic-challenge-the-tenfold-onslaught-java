package org.aldomanco.game_screen;

import org.aldomanco.display.Display;
import org.aldomanco.menu_screen.MenuScreen;
import org.aldomanco.sprite.SpriteAnimation;
import org.aldomanco.state.StateMachine;
import org.aldomanco.state.SuperStateMachine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by unieuro on 1/19/2020.
 */
public class ScoreScreen extends SuperStateMachine implements KeyListener {

    private SpriteAnimation wallpaper;
    private Rectangle rectangle;
    public static int state;

    private Font font = new Font("Black Ops One", Font.PLAIN, 48);

    public ScoreScreen(StateMachine stateMachine) {
        super(stateMachine);

        wallpaper = new SpriteAnimation(0, 0, 1, 23, 100, "/org/aldomanco/media/sprites/ScoreScreen.png");
        wallpaper.setWidth(800);
        wallpaper.setHeight(700);

        this.setRectangle(new Rectangle((int) wallpaper.getXPosition(), (int) wallpaper.getYPosition(), wallpaper.getWidth(), wallpaper.getHeight()));
        wallpaper.setLoop(true);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public void update(double lastLoopTicks) {

        wallpaper.update(lastLoopTicks);
    }

    @Override
    public void draw(Graphics2D graphic) {

        wallpaper.draw(graphic);

        graphic.setColor(Color.white);
        graphic.setFont(font);

        if (state == 1 && SinglePlayerScreen.SCORE>0) {
            String playerScore = "SCORE: " + SinglePlayerScreen.SCORE;
            int playerScoreWidth = graphic.getFontMetrics().stringWidth(playerScore);
            graphic.drawString(playerScore, (Display.WIDTH / 2) - (playerScoreWidth / 2), Display.HEIGHT / 2+220);
        } else if (state==1 && SinglePlayerScreen.SCORE==0){
            String playerScore = "GAME OVER";
            int playerScoreWidth = graphic.getFontMetrics().stringWidth(playerScore);
            graphic.drawString(playerScore, (Display.WIDTH / 2) - (playerScoreWidth / 2), Display.HEIGHT / 2+220);
        } else if (state == 2) {
            if (MultiPlayerScreen.firstPlayer_SCORE > MultiPlayerScreen.secondPlayer_SCORE) {
                String firstPlayerWon = "FIRST PLAYER WON";
                int firstPlayerWonWidth = graphic.getFontMetrics().stringWidth(firstPlayerWon);
                graphic.drawString(firstPlayerWon, (Display.WIDTH / 2) - (firstPlayerWonWidth / 2), Display.HEIGHT / 2+160);
            } else if (MultiPlayerScreen.firstPlayer_SCORE == MultiPlayerScreen.secondPlayer_SCORE) {
                String tied = "TIE";
                int tiedWidth = graphic.getFontMetrics().stringWidth(tied);
                graphic.drawString(tied, (Display.WIDTH / 2) - (tiedWidth / 2), Display.HEIGHT / 2+160);
            } else if (MultiPlayerScreen.firstPlayer_SCORE < MultiPlayerScreen.secondPlayer_SCORE) {
                String secondPlayerWon = "SECOND PLAYER WON";
                int secondPlayerWonWidth = graphic.getFontMetrics().stringWidth(secondPlayerWon);
                graphic.drawString(secondPlayerWon, (Display.WIDTH / 2) - (secondPlayerWonWidth / 2), Display.HEIGHT / 2+160);
            }

            String firstPlayerScore = "FIRST PLAYER SCORE: " + MultiPlayerScreen.firstPlayer_SCORE;
            int firstPlayerScoreWidth = graphic.getFontMetrics().stringWidth(firstPlayerScore);
            graphic.drawString(firstPlayerScore, (Display.WIDTH / 2) - (firstPlayerScoreWidth / 2), Display.HEIGHT / 2+230);

            String secondPlayerScore = "SECOND PLAYER SCORE: " + MultiPlayerScreen.secondPlayer_SCORE;
            int secondPlayerScoreWidth = graphic.getFontMetrics().stringWidth(secondPlayerScore);
            graphic.drawString(secondPlayerScore, (Display.WIDTH / 2) - (secondPlayerScoreWidth / 2), Display.HEIGHT / 2 + 300);
        }
    }

    @Override
    public void initialize(Canvas canvas) {
        canvas.addKeyListener(this);
    }

    @Override
    public StateMachine getStateMachine() {
        return super.getStateMachine();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode()!=0) {

            getStateMachine().setState((byte) 0);

            SinglePlayerScreen.stopSpaceSound();
            MenuScreen.playExperience();

            MultiPlayerScreen.firstPlayer_SCORE = 0;
            MultiPlayerScreen.secondPlayer_SCORE = 0;
            MultiPlayerScreen.whichPlayer = 1;

            SinglePlayerScreen.SCORE = 0;
        }
    }
}
