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
	
	@Override
	public void process() {
		mY += game.gravity;
		y += mY;
	}
	
	@Override
	public void touch() {
		if(mY > 0)
			mY *= -game.touch.bounce;
		else
			mY *= game.touch.bounce;
	}
}
