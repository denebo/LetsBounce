package com.example.letsbounce;

import android.graphics.Bitmap;

public class Falling extends Entity {
	int health;
	float gravity, mX, mY;
	
	public Falling(float x, float y, float width, float height, float gravity, Bitmap bmap) {
		super(x, y, width, height, bmap);
		this.gravity = gravity;
		health = 3;
	}

	public void setGravity(float gravity) { this.gravity = gravity; }
	public float getGravity() { return this.gravity; }
	
	@Override
	public void process() {
		mY += gravity;
		setY(getY() + mY);
		
	}
	
	public void touch() {
		
	}
}
