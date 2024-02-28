package org.toysjourney.object;

import java.awt.geom.Rectangle2D;

import static org.toysjourney.utilz.Constants.ANI_SPEED;
import static org.toysjourney.utilz.Constants.ObjectConstants.GetSpriteAmount;

public class GameObject {

    protected int worldX, worldY, objType;
    protected Rectangle2D.Float hitBox;
    protected boolean doAnimation, active = true;
    protected int aniTick, aniIndex;
    protected int xDrawOffset, yDrawOffset;

    public GameObject(int x, int y, int objType) {
        worldX = x;
        worldY = y;
        this.objType = objType;
    }

    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(objType)) {
                aniIndex = 0;

                // FILM: obj don't have animation
//				if (objType == BARREL || oblType == BOX) {
//					doAnimation = false;
//					active = false;
//				}
            }
        }
    }

    public void reset() {
        aniIndex = 0;
        aniTick = 0;
        active = true;

        // FILM: obj don't have animation
//		if (objType == BARREL || oblType == BOX) {
//			doAnimation = false;
//			active = false;
//		}
//		else
        doAnimation = true;
    }

    protected void initHitBox(float width, float height) {
        hitBox = new Rectangle2D.Float(worldX, worldY, width, height);
    }


    public int getObjType() {
        return objType;
    }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getAniIndex() {
        return aniIndex;
    }


}

