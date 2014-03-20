package com.example.letsbounce;

import android.graphics.Bitmap;
import android.util.Log;

public class Falling extends Entity {
	int health;
	float gravity, mX, mY, bounce;
	
	public Falling(LetsBounce game, float x, float y, float width, float height, float gravity, float bounce, Bitmap bmap) {
		super(game, x, y, width, height, bmap);
		this.gravity = gravity;
		this.bounce = bounce;
		health = 3;
	}
	
	public float getCenterX() { return x + width / 2; }
	public float getCenterY() { return y + height / 2; }
	
	@Override
	public void process() {
		super.process();
		mY += game.gravity;
		y += mY;
		x += mX;
		
		if(x + width > game.SCREEN_WIDTH || x < 0)
			mX = -mX;
	}
	
	@Override
	public void touch() {		
		float dX = game.touch.x - getCenterX();
		float dY = Math.abs(game.touch.y - y);
		
		double angle = Math.atan2(dY, dX);
		float fX = (float)Math.cos(angle) * game.touch.bounce;
		float fY = (float)Math.abs(Math.sin(angle)) * game.touch.bounce;

		mX -= fX;
		mY -= fY;
		
		Log.d("ASDF", fX + ", " + fY);
	}
}
