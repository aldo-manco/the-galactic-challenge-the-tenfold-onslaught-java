package org.aldomanco.state;

import org.aldomanco.game_screen.AboutScreen;
import org.aldomanco.game_screen.SinglePlayerScreen;
import org.aldomanco.game_screen.MultiPlayerScreen;
import org.aldomanco.game_screen.ScoreScreen;
import org.aldomanco.menu_screen.MenuScreen;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by unieuro on 12/23/2019.
 */
public class StateMachine {

    private ArrayList<SuperStateMachine> states = new ArrayList<SuperStateMachine>();
    private Canvas canvas;
    private byte selectState = 0;

    public StateMachine(Canvas canvas) {
        SuperStateMachine game = new SinglePlayerScreen(this);
        SuperStateMachine multiplayer = new MultiPlayerScreen(this);
        SuperStateMachine menu = new MenuScreen(this);
        SuperStateMachine scoreScreen = new ScoreScreen(this);
        SuperStateMachine aboutScreen = new AboutScreen(this);
        states.add(menu);
        states.add(game);
        states.add(multiplayer);
        states.add(scoreScreen);
        states.add(aboutScreen);

        this.canvas = canvas;
    }

    public void draw(Graphics2D graphic){
        states.get(selectState).draw(graphic);
    }

    public void update(double lastLoopTicks){
        states.get(selectState).update(lastLoopTicks);
    }

    public void setState(byte newState){

        for (int i = 0; i < canvas.getKeyListeners().length; i++) {
            canvas.removeKeyListener(canvas.getKeyListeners()[i]);
        }

        selectState = newState;
        states.get(selectState).initialize(canvas);
    }

    public byte getState(){
        return selectState;
    }
}
