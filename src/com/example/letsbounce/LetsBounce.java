package com.example.letsbounce;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

public class LetsBounce {
	int SCREEN_WIDTH, SCREEN_HEIGHT;
	Context context;
	Touch touch;
	float gravity, mYCap, mYPad, mXCap, density;
	
	ArrayList<Entity> entities;
	
	public LetsBounce(Context context, Touch touch, float gravity) {
		this.context = context;
		this.touch = touch;
		this.gravity = gravity;
		mYCap = 10.0f;
		mYPad = 5.0f;
		mXCap = 8.0f;
		density = context.getResources().getDisplayMetrics().density;
		entities = new ArrayList<Entity>();
		SCREEN_WIDTH = context.getResources().getDisplayMetrics().widthPixels;
		SCREEN_HEIGHT = context.getResources().getDisplayMetrics().heightPixels;
		
		// adding falling objects
		entities.add(new Falling(this, 50, 0, 64, 64, 0, 0, BitmapFactory.decodeResource(context.getResources(), R.drawable.red)));
		entities.add(new Falling(this, 200, 0, 64, 64, 0, 0, BitmapFactory.decodeResource(context.getResources(), R.drawable.red)));
		entities.add(new Falling(this, 300, 0, 64, 64, 0, 0, BitmapFactory.decodeResource(context.getResources(), R.drawable.red)));
		entities.add(new Falling(this, 200, 100, 96, 96, 0, 0, BitmapFactory.decodeResource(context.getResources(), R.drawable.green)));
	}
	
	public void process() {
		for(Entity e : entities)
			e.process();
		
		// check for collisions between falling objects and make them bounce
		// at respective angles
		for(int i = 0; i < entities.size(); i++) {
			Falling e = (Falling)entities.get(i);
			for (int j = i; j < entities.size(); j++) {
				Falling ec = (Falling)entities.get(j);
				if(e != ec) {
					float dX = e.x - ec.x;
					float dY = e.y - ec.y;
					double hyp = Math.sqrt(dX * dX + dY * dY);
					
					if(Math.abs(hyp) < e.width / 2 + ec.width / 2) {
						double angle = Math.atan2(dY, dX);
						float fX = (float)Math.cos(angle) * touch.bounce;
						float fY = (float)Math.sin(angle) * touch.bounce;

						e.mX += fX;
						e.mY += fY;
						ec.mX -= fX;
						ec.mY -= fY;
					}
				}
			}
		}
		
		touch.clicking = false; // for synchronization
	}
}
