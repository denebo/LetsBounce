package com.example.letsbounce;

import android.graphics.Bitmap;

public class Entity {
	private float x, y;
	private Bitmap bmap;
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Entity(int x, int y, Bitmap bmap) {
		this.x = x;
		this.y = y;
		this.bmap = bmap;
	}
	
	public float getX() { return x; }
	public void setX(int x) { this.x = x; }
	public float getY() { return y; }
	public void setY(int y) { this.y = y; }
}
