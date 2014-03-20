package com.example.letsbounce;

import android.graphics.Bitmap;

public class Entity {
	float x, y, width, height;
	Bitmap bmap;
	
	
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
	
	public void touch(Touch touch) {
		
	}
	
	public void process() {
		
	}
}
