package com.example.letsbounce;

import android.graphics.Bitmap;

public class FDestroyable extends FObject {
	int health, score;
	
	public FDestroyable(GameScene scene, float x, float y, float width, float height, float bounce, Bitmap bmap, int health, int score) {
		super(scene, x, y, width, height, bounce, bmap);
		
		this.health = health;
		this.score = score;
	}
	
	@Override
	public void touch() {
		super.touch();
		health -= 1;
		if(health <= 0) {
			for(int i = 0; i < scene.entities.size(); i++) {
				Entity e = scene.entities.get(i);
				if(e == this)
					e.y = scene.game.SCREEN_HEIGHT + 100; // remove entity
			}
		}
	}
}
