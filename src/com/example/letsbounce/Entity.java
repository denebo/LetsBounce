package com.example.letsbounce;

import android.graphics.Bitmap;
import android.util.Log;

public class Entity {
	float x, y, width, height;
	Bitmap bmap;
	LetsBounce game;
	
	public Entity(LetsBounce game, float x, float y, float width, float height) {
		this.x = x * game.density;
		this.y = y * game.density;
		this.width = width * game.density;
		this.height = height * game.density;
		this.game = game;
	}

	public Entity(LetsBounce game, float x, float y,  float width, float height, Bitmap bmap) {
		this.x = x * game.density;
		this.y = y * game.density;
		this.width = width * game.density;
		this.height = height * game.density;
		this.bmap = bmap;
		this.game = game;
	}
	
	public float getCenterX() { return x + width / 2; }
	public float getCenterY() { return y + height / 2; }
	
	public void touch() {
		
	}
	
	public void process() {
		if(game.touch.counter > 0) {
			if(game.touch.click()) {		
				if(game.touch.isTouching(this))
					touch();
			}
		}
	}
}
