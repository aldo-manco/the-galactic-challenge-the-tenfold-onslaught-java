package org.aldomanco.display;

import org.aldomanco.game_screen.SinglePlayerScreen;
import org.aldomanco.menu_screen.MenuScreen;
import org.aldomanco.state.StateMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

/**
 * Created by unieuro on 12/22/2019.
 */
public class Display extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    public static JFrame frame = new JFrame("Space Invaders");

    public static void main(String[] args) {

        Display display = new Display();
        //frame.add(buttonSinglePlayer);
        //frame.add(buttonMultiPlayer);
        frame.add(display);
        frame.pack();
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                System.err.println("Exiting Game");

                if (MenuScreen.isRunningSound()) {
                    MenuScreen.stopExperience();
                }

                if (!MenuScreen.isRunningSound()) {
                    SinglePlayerScreen.stopSpaceSound();
                }

                display.stop();
                System.exit(0);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.requestFocus();
        display.start();

        /*ListenerMouse listenerMouse = new ListenerMouse();

        buttonSinglePlayer = new JButton("Single Player");
        buttonSinglePlayer.setBounds(300, 250, 150, 50);
        buttonSinglePlayer.setBackground(Color.black);
        buttonSinglePlayer.addMouseListener(listenerMouse);

        buttonMultiPlayer = new JButton("Multi Player");
        buttonMultiPlayer.setBounds(200, 450, 100, 30);
        buttonMultiPlayer.addMouseListener(listenerMouse);*/
    }

    private boolean isRunning = false;
    private Thread thread;

    public static StateMachine state;
    //public static JButton buttonSinglePlayer;
    //public static JButton buttonMultiPlayer;

    public boolean isRunning() {
        return isRunning;
    }

    public synchronized void start() {

        if (isRunning) {
            return;
        }

        isRunning = true;

        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {

        if (!isRunning) {
            return;
        }

        isRunning = false;

        try {
            thread.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int WIDTH = 800, HEIGHT = 700;
    public int FPS;

    public Display() {
        this.setSize(WIDTH, HEIGHT);
        this.setFocusable(true);

        state = new StateMachine(this);
        state.setState((byte) 0);
    }

    @Override
    public void run() {
        long timer = System.currentTimeMillis();
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long NANOSECONDS_PER_TICK = 1_000_000_000 / TARGET_FPS;
        int frames = 0;

        this.createBufferStrategy(3);
        BufferStrategy bufferStrategy = this.getBufferStrategy();

        while (isRunning) {
            long now = System.nanoTime();
            long updatedLength = now - lastLoopTime;
            lastLoopTime = now;
            double lastLoopTicks = updatedLength / (double) NANOSECONDS_PER_TICK;

            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                FPS = frames;
                frames = 0;
                System.out.println(FPS);
            }

            draw(bufferStrategy);
            update(lastLoopTicks);

            try {
                Thread.sleep((lastLoopTime - System.nanoTime() + NANOSECONDS_PER_TICK) / 1_000_000);
            } catch (Exception e) {

            }
        }
    }

    public void draw(BufferStrategy bufferStrategy) {

        do {
            do {
                Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
                graphics.setColor(Color.black);
                graphics.fillRect(0, 0, WIDTH + 50, HEIGHT + 50);

                state.draw(graphics);

                graphics.dispose();
            } while (bufferStrategy.contentsRestored());

            bufferStrategy.show();

        } while (bufferStrategy.contentsLost());
    }

    public void update(double lastLoopTicks) {
        state.update(lastLoopTicks);
    }

    /*private static class ListenerMouse extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {

            JButton button = (JButton) e.getSource();

            if (button.equals(buttonSinglePlayer)) {
                MenuScreen.experience.stop();
                SinglePlayerScreen.floatingInSpace = new Sound("/org/aldomanco/media/sounds/floatingInSpace.wav");
                SinglePlayerScreen.floatingInSpace.play();
                SinglePlayerScreen.floatingInSpace.loop();
                state.setState((byte) 1);
            }

            if (button.equals(buttonMultiPlayer)) {
                MenuScreen.experience.stop();
                SinglePlayerScreen.floatingInSpace = new Sound("/org/aldomanco/media/sounds/floatingInSpace.wav");
                SinglePlayerScreen.floatingInSpace.play();
                SinglePlayerScreen.floatingInSpace.loop();
                state.setState((byte) 2);

            }
            buttonSinglePlayer.setVisible(false);
            buttonMultiPlayer.setVisible(false);
        }
    }*/
}
