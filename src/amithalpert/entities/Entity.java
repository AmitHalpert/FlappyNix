package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public abstract class Entity extends Thread{

	protected float x;
	protected float y;
	protected int width, height;
	protected Rectangle2D.Float hitbox;

	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

	protected void drawHitbox(Graphics g) {
		// For debugging the hitbox
		g.setColor(Color.RED);
		g.drawRect((int) hitbox.x + 9, (int) hitbox.y, (int) hitbox.width - 14, (int) ((int) hitbox.height - (y / 2) + 55));
	}

	protected void initHitbox(float x, float y, float width, float height) {
		hitbox = new Rectangle2D.Float(x, y, width, height);
	}


	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}


}
