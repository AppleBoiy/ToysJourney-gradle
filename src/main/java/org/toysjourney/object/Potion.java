package org.toysjourney.object;

import org.toysjourney.Game;

import java.awt.*;

public class Potion extends GameObject {

    public Potion(Point p, int objType) {
        super(p.x, p.y, objType);
        doAnimation = true;
        // blue_potion_size
        initHitBox(7f, 14f);

        xDrawOffset = (int) (3 * Game.SCALE); // 3 pix to the left
        yDrawOffset = (int) (2 * Game.SCALE); // 2 pix to the top

    }

    public void update() {
        updateAnimationTick();
    }


}