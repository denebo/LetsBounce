package com.example.letsbounce;

import android.graphics.Bitmap;
import android.util.Log;

public class Entity {
	float x, y, width, height;
	Bitmap bmap;
	Scene scene;
	
	public Entity(Scene scene, float x, float y, float width, float height) {
		this.scene = scene;
		this.x = x * scene.game.density;
		this.y = y * scene.game.density;
		this.width = width * scene.game.density;
		this.height = height * scene.game.density;
	}

	public Entity(Scene scene, float x, float y,  float width, float height, Bitmap bmap) {
		this.scene = scene;
		this.x = x * scene.game.density;
		this.y = y * scene.game.density;
		this.width = width * scene.game.density;
		this.height = height * scene.game.density;
		this.bmap = bmap;
	}
	
	public float getCenterX() { return x + width / 2; }
	public float getCenterY() { return y + height / 2; }
	
	public void touch() {
		
	}
	
	public void process() {
		if(scene.game.touch.counter > 0) {
			if(scene.game.touch.click()) {		
				if(scene.game.touch.isTouching(this))
					touch();
			}
		}
	}
}
