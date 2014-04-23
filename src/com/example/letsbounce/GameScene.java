package com.example.letsbounce;

import android.graphics.BitmapFactory;

public class GameScene extends Scene {
	public float gravity, mYCap, mYPad, mXCap;
	public int score;
	
	public GameScene(LetsBounce game) {
		super(game);
		gravity = 0.1f;
		mYCap = 15.0f;
		mYPad = 5.0f;
		mXCap = 8.0f;
		
		score = 0;
		
		// adding falling objects
		entities.add(new FObject(this, 50, 0, 64, 64, 0, 0, BitmapFactory.decodeResource(game.context.getResources(), R.drawable.red)));
		entities.add(new FObject(this, 50, 0, 64, 64, 0, 0, BitmapFactory.decodeResource(game.context.getResources(), R.drawable.red)));
		entities.add(new FObject(this, 50, 0, 64, 64, 0, 0, BitmapFactory.decodeResource(game.context.getResources(), R.drawable.red)));
		entities.add(new FObject(this, 50, 100, 96, 96, 0, 0, BitmapFactory.decodeResource(game.context.getResources(), R.drawable.green)));
	}
	
	public void process() {
		super.process();
		// check for collisions between falling objects and make them bounce
		// at respective angles
		for(int i = 0; i < entities.size(); i++) {
			FObject e = (FObject)entities.get(i);
			for (int j = i; j < entities.size(); j++) {
				FObject ec = (FObject)entities.get(j);
				if(e != ec) {
					float dX = e.getCenterX() - ec.getCenterX();
					float dY = e.getCenterY() - ec.getCenterY();
					double hyp = Math.sqrt(dX * dX + dY * dY);
					
					if(Math.abs(hyp) < e.width / 2 + ec.width / 2) {
						double angle = Math.atan2(dY, dX);
						float fX = (float)Math.cos(angle) * game.touch.bounce;
						float fY = (float)Math.sin(angle) * game.touch.bounce;
	
						e.mX += fX;
						e.mY += fY;
						ec.mX -= fX;
						ec.mY -= fY;
					}
				}
			}
		}
	}
}
