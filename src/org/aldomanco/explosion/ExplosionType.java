package org.aldomanco.explosion;

import java.awt.*;

/**
 * Created by unieuro on 1/4/2020.
 */
public interface ExplosionType {

    public void draw(Graphics2D graphics);
    public void update(double lastLoopTicks);

    public boolean destroy();
}
