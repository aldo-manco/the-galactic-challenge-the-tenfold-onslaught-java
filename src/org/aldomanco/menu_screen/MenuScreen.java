package org.aldomanco.menu_screen;

import org.aldomanco.display.Display;
import org.aldomanco.game_screen.SinglePlayerScreen;
import org.aldomanco.sound.Sound;
import org.aldomanco.sprite.SpriteAnimation;
import org.aldomanco.state.StateMachine;
import org.aldomanco.state.SuperStateMachine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by unieuro on 12/23/2019.
 */
public class MenuScreen extends SuperStateMachine implements KeyListener {

    private Font fontTitle = new Font("Press Start 2P Regular", Font.PLAIN, 48);
    private Font fontMenu = new Font("Press Start 2P Regular", Font.PLAIN, 24);

    private String title = "Space Invaders";
    private String singlePlayer = "Press Enter - Single Player";
    private String multiPlayer = "Press Space - Two Players";
    private String about = "Press A - About";
    private String exit = "Press Esc - Exit";

    private SpriteAnimation wallpaper;
    private Rectangle rectangle;

    private static Sound experience;

    public MenuScreen(StateMachine stateMachine) {
        super(stateMachine);

        experience = new Sound("/org/aldomanco/media/sounds/experience.wav");
        playExperience();

        wallpaper = new SpriteAnimation(0, 0, 1, 13, 100, "/org/aldomanco/media/sprites/GiphyRevolution.png");
        wallpaper.setWidth(800);
        wallpaper.setHeight(700);

        this.setRectangle(new Rectangle((int) wallpaper.getXPosition(), (int) wallpaper.getYPosition(), wallpaper.getWidth(), wallpaper.getHeight()));
        wallpaper.setLoop(true);
    }

    @Override
    public void draw(Graphics2D graphic) {

        wallpaper.draw(graphic);

        graphic.setFont(fontTitle);
        int widthTitle = graphic.getFontMetrics().stringWidth(title);
        graphic.setColor(Color.RED);
        graphic.drawString(title, ((Display.WIDTH / 2) - (widthTitle / 2)) - 2, (Display.HEIGHT / 2) - 112);

        graphic.setColor(Color.ORANGE);
        graphic.drawString(title, ((Display.WIDTH / 2) - (widthTitle / 2)), (Display.HEIGHT / 2) - 114);

        graphic.setFont(fontMenu);
        int singlePlayerWidth = graphic.getFontMetrics().stringWidth(singlePlayer);
        graphic.setColor(Color.WHITE);
        graphic.drawString(singlePlayer, (Display.WIDTH / 2) - (singlePlayerWidth / 2), (Display.HEIGHT / 2) + 75);

        graphic.setFont(fontMenu);
        int multiPlayerWidth = graphic.getFontMetrics().stringWidth(multiPlayer);
        graphic.setColor(Color.WHITE);
        graphic.drawString(multiPlayer, (Display.WIDTH / 2) - (multiPlayerWidth / 2), (Display.HEIGHT / 2) + 150);

        graphic.setFont(fontMenu);
        int aboutWidth = graphic.getFontMetrics().stringWidth(about);
        graphic.setColor(Color.WHITE);
        graphic.drawString(about, (Display.WIDTH / 2) - (aboutWidth / 2), (Display.HEIGHT / 2) + 225);

        graphic.setFont(fontMenu);
        int exitWidth = graphic.getFontMetrics().stringWidth(exit);
        graphic.setColor(Color.WHITE);
        graphic.drawString(exit, (Display.WIDTH / 2) - (exitWidth / 2), (Display.HEIGHT / 2) + 300);
    }

    @Override
    public void initialize(Canvas canvas) {
        canvas.addKeyListener(this);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

        if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
            stopExperience();
            SinglePlayerScreen.playSpaceSound();
            getStateMachine().setState((byte) 1);
        }else if (keyEvent.getKeyCode()==KeyEvent.VK_SPACE){
            stopExperience();
            SinglePlayerScreen.playSpaceSound();
            getStateMachine().setState((byte) 2);
        }else if(keyEvent.getKeyCode()==KeyEvent.VK_A){
            getStateMachine().setState((byte) 4);
        }else if(keyEvent.getKeyCode()==KeyEvent.VK_ESCAPE){
            System.exit(0);
        }else if ((keyEvent.getKeyCode()==KeyEvent.VK_M)){
            if (experience.isPlaying()){
                stopExperience();
            }else if (!experience.isPlaying()){
                playExperience();
            }
        }
    }

    @Override
    public void update(double lastLoopTicks) {

        wallpaper.update(lastLoopTicks);
        //wallpaper.setXPosition(wallpaper.getXPosition() - (lastLoopTicks));
        this.getRectangle().x = (int) wallpaper.getXPosition();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public static boolean isRunningSound() {
        return experience.isPlaying();
    }

    public static void playExperience(){
        experience.play();
        experience.loop();
    }

    public static void stopExperience() {
        experience.stop();
    }
}