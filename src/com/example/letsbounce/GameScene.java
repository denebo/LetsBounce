package com.example.letsbounce;

import android.graphics.BitmapFactory;
import android.util.Log;

public class GameScene extends Scene {
	public float gravity, mYCap, mYPad, mXCap;
	Label scoreLabel;
	public int score;
	boolean spawned;
	
	public GameScene(LetsBounce game) {
		super(game);
		gravity = 1.0f;
		mYCap = 15.0f;
		mYPad = 5.0f;
		mXCap = 8.0f;
		score = 0;
		spawned = false;
	}
	
	@Override
	public void initialize() {
		scoreLabel = new Label(this, 100, 100, "Score: 0", 100.0f);
		entities.add(scoreLabel);
	}
	
	@Override
	public void process() {
		super.process();
		// check for collisions between falling objects and make them bounce
		// at respective angles
		for(int i = 0; i < entities.size(); i++) {
			Entity e_ = entities.get(i);
			if(e_ instanceof FObject) {
				FObject e = (FObject)entities.get(i);
				for (int j = i; j < entities.size(); j++) {
					Entity ec_ = entities.get(j);
					if(ec_ instanceof FObject) {
						FObject ec = (FObject)entities.get(j);
						if(e != ec) {
							float dX = e.getCenterX() - ec.getCenterX();
							float dY = e.getCenterY() - ec.getCenterY();
							double hyp = Math.sqrt(dX * dX + dY * dY);
							
							if(Math.abs(hyp) < e.width / 2 + ec.width / 2) {
								double angle = Math.atan2(dY, dX);
								float fX = (float)Math.cos(angle) * ((e.bounce / 2 + ec.bounce / 2) / 2);
								float fY = (float)Math.sin(angle) * ((e.bounce / 2 + ec.bounce / 2) / 2);
			
								e.mX += fX;
								e.mY += fY;
								ec.mX -= fX;
								ec.mY -= fY;
							}
						}
					}
				}
			}
			scoreLabel.label = "Score: " + score;
		}
		
		// remove objects that fall below screen
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if(e.y > game.SCREEN_HEIGHT) {
				if(e instanceof FDestroyable)
					score -= ((FDestroyable) e).score;
				entities.remove(i);
			}
		}
		
		if((score % 5 == 0) && !spawned) {
			spawned = true;
			entities.add(new FDestroyable(this, 50, -96.0f, 64, 64, 15.0f, BitmapFactory.decodeResource(game.context.getResources(), R.drawable.red), 5, 1));
			entities.add(new FDestroyable(this, 50, -96.0f, 96, 96, 15.0f, BitmapFactory.decodeResource(game.context.getResources(), R.drawable.green), 5, 1));
		}
		
		Log.d("ASDF", entities.size() + "");
	}
	
	@Override
	public void touched(Entity e) {
		spawned = false;
		if(e instanceof FDestroyable) {
			score += ((FDestroyable) e).score;
		}
	}
}
