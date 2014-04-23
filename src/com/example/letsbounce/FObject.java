package com.example.letsbounce;

import android.graphics.Bitmap;
import android.util.Log;

public class FObject extends Entity {
	GameScene scene;
	int health;
	float gravity, mX, mY, bounce;
	
	public FObject(GameScene scene, float x, float y, float width, float height, float gravity, float bounce, Bitmap bmap) {
		super(scene, x, y, width, height, bmap);
		this.gravity = gravity;
		this.bounce = bounce;
		this.scene = scene;
	}
	
	@Override
	public void process() {
		super.process();
		
		mY += scene.gravity;
		
//		if(mY > scene.mYCap)
//			mY = scene.mYCap;
//		else if(mY < -scene.mYCap - scene.mYPad)
//			mY = -scene.mYCap;
		
		if(mX > scene.mXCap)
			mX = scene.mXCap;
		
		// ceiling
		if(y < 0) mY = -mY;
		
		// left wall
		if(x < 0) {
			mX = -mX;
			x = 0;
		}
		
		// right wall
		if(x + width > scene.game.SCREEN_WIDTH) {
			mX = -mX;
			x = scene.game.SCREEN_WIDTH - width;
		}
		
		y += mY;
		x += mX;
	}
	
	@Override
	public void touch() {		
		float dX = scene.game.touch.x - getCenterX(); // we need to move left and right, so no abs() for dX
		float dY = Math.abs(scene.game.touch.y - y);
		
		double angle = Math.atan2(dY, dX);
		float fX = (float)Math.cos(angle) * scene.game.touch.bounce;
		float fY = (float)Math.abs(Math.sin(angle)) * scene.game.touch.bounce;

		mX -= fX;
		mY -= fY + scene.score;
		
		// update score
		scene.score += 1;
	}
}
