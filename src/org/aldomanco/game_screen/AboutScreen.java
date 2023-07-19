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
public class AboutScreen extends SuperStateMachine implements KeyListener {

    private SpriteAnimation wallpaper;
    private Rectangle rectangle;
    public static int state;

    private Font font = new Font("Press Start 2P Regular", Font.PLAIN, 24);

    public AboutScreen(StateMachine stateMachine) {
        super(stateMachine);

        wallpaper = new SpriteAnimation(0, 0, 1, 29, 100, "/org/aldomanco/media/sprites/About.png");
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

        graphic.setColor(Color.BLUE);
        graphic.setFont(font);

        String developer = "MADE BY ALDO MANCO - 159165 ";
        int developerWidth = graphic.getFontMetrics().stringWidth(developer);
        graphic.drawString(developer, (Display.WIDTH / 2) - (developerWidth / 2), Display.HEIGHT / 2 + 48);

        graphic.setColor(Color.CYAN);
        graphic.drawString(developer, (Display.WIDTH / 2) - (developerWidth / 2), Display.HEIGHT / 2 + 46);
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
        }
    }
}
