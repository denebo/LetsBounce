package com.example.letsbounce;

import android.graphics.Bitmap;

public class FDestroyable extends FObject {
	int health;
	
	public FDestroyable(GameScene scene, float x, float y, float width, float height, float bounce, float gravity, Bitmap bmap, int health) {
		super(scene, x, y, width, height, bounce, gravity, bmap);
		
		this.health = health;
	}
	
	@Override
	public void touch() {
		if(!scene.gameOver) {
			super.touch();
			health -= 1;
			if(health <= 0) {
				for(int i = 0; i < scene.entities.size(); i++) {
					Entity e = scene.entities.get(i);
					if(e == this) {
						e.y = scene.game.SCREEN_HEIGHT + 200; // remove entity
						if(e.width == 96 * scene.game.density)
							((GameScene)e.scene).lives += 1;
					}
				}
			}
		}
	}
}
