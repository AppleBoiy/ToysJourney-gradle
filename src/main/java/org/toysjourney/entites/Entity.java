package org.toysjourney.entites;

import org.toysjourney.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
	
	protected float x, y;
	protected int width, height;
	protected Rectangle2D.Float hitbox;
	private float xDrawOffset = 9.5f * Game.SCALE;
	private float yDrawOffset = 6 * Game.SCALE;
	
	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	protected void drawhitbox(Graphics g) {
		g.setColor(Color.pink);
		g.drawRect((int) (hitbox.x + xDrawOffset), (int) (hitbox.y + yDrawOffset), (int) (hitbox.width), (int) (hitbox.height));
	}
	
	protected void initHitbox(float x, float y, int width, int height) {
		hitbox = new Rectangle2D.Float(x, y, width, height);
		System.out.println(hitbox.x);
	}

//	protected void updateHitbox() {
//		hitbox.x = (int) x;
//		hitbox.y = (int) y;
//	}

	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}

}
