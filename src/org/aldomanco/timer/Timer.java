package org.aldomanco.timer;

// Catch - Timer ---

/**
 * Created by unieuro on 1/3/2020.
 */
public class Timer {

    private long startTime;

    public Timer(){
        setStartTime(System.currentTimeMillis());
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long currentTime) {
        this.startTime = currentTime;
    }

    public void resetTimer(){
        setStartTime(System.currentTimeMillis());
    }

    public boolean timerEvent(int timer){

        if (System.currentTimeMillis() - getStartTime() > timer){
            resetTimer();
            return true;
        }
        return false;
    }

    public boolean isTimerReady(int timer){

        if (System.currentTimeMillis() - getStartTime() > timer){
            return true;
        }
        return false;
    }
}