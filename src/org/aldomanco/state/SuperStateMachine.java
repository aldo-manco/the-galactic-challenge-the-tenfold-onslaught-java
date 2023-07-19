package org.aldomanco.state;

import java.awt.*;

/**
 * Created by unieuro on 12/23/2019.
 */
public abstract class SuperStateMachine {

    private StateMachine stateMachine;

    public SuperStateMachine(StateMachine stateMachine){
        this.stateMachine = stateMachine;
    }

    public abstract void update(double lastLoopTicks);
    public abstract void draw(Graphics2D graphic);
    public abstract void initialize(Canvas canvas);

    public StateMachine getStateMachine(){
        return stateMachine;
    }
}
