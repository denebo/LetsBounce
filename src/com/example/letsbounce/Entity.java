package com.example.letsbounce;

import android.graphics.Bitmap;

public class Entity {
	float x, y, width, height;
	Bitmap bmap;
	LetsBounce game;
	
	public Entity(LetsBounce game, float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.game = game;
	}

	public Entity(LetsBounce game, float x, float y,  float width, float height, Bitmap bmap) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bmap = bmap;
		this.game = game;
	}
	
	public void touch() {
		
	}
	
	public void process() {
		
	}
}
