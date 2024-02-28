package org.toysjourney.object;

import java.awt.*;

public class Key extends GameObject {


    public Key(Point p, int objType) {
        super(p.x, p.y, objType);
        doAnimation = true;
        // blue_potion_size
//		xDrawOffset = (int) (3 * Game.SCALE); // 3 pix to the left
//		yDrawOffset = (int) (2 * Game.SCALE); // 2 pix to the top

    }


    public void update() {
        updateAnimationTick();
    }
}
