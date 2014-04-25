package com.example.letsbounce;

import android.graphics.BitmapFactory;

public class GameScene extends Scene {
	public float gravity, mYCap, mYPad, mXCap;
	Label scoreLabel;
	public int score;
	
	public GameScene(LetsBounce game) {
		super(game);
		gravity = 0.32f;
		mYCap = 15.0f;
		mYPad = 5.0f;
		mXCap = 8.0f;
		score = 0;
	}
	
	@Override
	public void initialize() {
		entities.add(new FObject(this, 50, 100, 96, 96, 0, 15.0f, BitmapFactory.decodeResource(game.context.getResources(), R.drawable.green)));
		entities.add(new FObject(this, 50, 100, 96, 96, 0, 15.0f, BitmapFactory.decodeResource(game.context.getResources(), R.drawable.green)));
		entities.add(new FObject(this, 50, 100, 96, 96, 0, 15.0f, BitmapFactory.decodeResource(game.context.getResources(), R.drawable.green)));
		
		scoreLabel = new Label(this, 100, 100, "Score: 0", 100.0f);
		entities.add(scoreLabel);
	}
	
	public void process() {
		super.process();
		score = 0;
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
				score += e.touched;
			}
			scoreLabel.label = "Score: " + score;
		}
	}
}
