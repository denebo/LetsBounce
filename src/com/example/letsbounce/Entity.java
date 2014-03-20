package com.example.letsbounce;

import android.graphics.Bitmap;

public class Entity {
	private float x, y, width, height;
	private Bitmap bmap;
	
	
	public Entity(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Entity(float x, float y,  float width, float height, Bitmap bmap) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bmap = bmap;
	}
	
	public float getX() { return x; }
	public void setX(float x) { this.x = x; }
	public float getY() { return y; }
	public void setY(float y) { this.y = y; }
	public Bitmap getBitmap() { return this.bmap; }
	
	public void process() {
		
	}
}
