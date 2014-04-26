package com.example.letsbounce;

import android.graphics.Bitmap;
import android.util.Log;

public class FObject extends Entity {
	GameScene scene;
	int touched;
	float mX, mY, bounce;
	boolean spawning;
	
	public FObject(GameScene scene, float x, float y, float width, float height, float bounce, Bitmap bmap) {
		super(scene, x, y, width, height, bmap);
		this.bounce = bounce;
		this.scene = scene;
		touched = 0;
		spawning = true;
	}
	
	@Override
	public void process() {
		super.process();
		
		mY += scene.gravity;
		y += mY;
		x += mX;
		
		if(!spawning) {
	//		if(mY > scene.mYCap)
	//			mY = scene.mYCap;
	//		else if(mY < -scene.mYCap - scene.mYPad)
	//			mY = -scene.mYCap;
			
			if(mX > scene.mXCap)
				mX = scene.mXCap;
			
			// ceiling
			if(y < 0) {
				mY = -mY;
				y = 0;
			}
			
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
		} else {
			if(y > 0)
				spawning = false;
		}
	}
	
	@Override
	public void touch() {
		scene.touched(this); // tell the scene this entity has been touched
		if(!spawning) {
			float dX = scene.game.touch.x - getCenterX(); // we need to move left and right, so no abs() for dX
			float dY = Math.abs(scene.game.touch.y - y);
			mX = 0;
			mY = 0;
			
			double angle = Math.atan2(dY, dX);
			float fX = (float)Math.cos(angle) * bounce;
			float fY = (float)Math.abs(Math.sin(angle)) * bounce; // + mY is for more responsive bouncing on touch
	
			mX -= fX;
			mY -= fY;
			
			touched += 1;
		}
	}
}
