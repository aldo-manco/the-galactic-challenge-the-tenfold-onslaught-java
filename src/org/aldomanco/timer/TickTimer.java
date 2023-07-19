package org.aldomanco.timer;

// Support - Tick Timer ---

/**
 * Created by unieuro on 1/3/2020.
 */
public class TickTimer {

    private double tick, tickTarget;

    public TickTimer(double tickTarget) {
        this.tickTarget = tickTarget;
        this.tick = 0;
    }

    public void tick(double lastLoopTicks) {

        if (tick <= tickTarget) {
            tick += 1 * lastLoopTicks;
        }
    }

    public boolean isEventReady() {

        if (tick >= tickTarget) {
            resetTickTimer();
            return true;
        }
        return false;
    }

    private void resetTickTimer() {
        tick = 0;
    }
}
