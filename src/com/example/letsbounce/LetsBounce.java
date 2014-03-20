package com.example.letsbounce;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

public class LetsBounce {
	int SCREEN_WIDTH, SCREEN_HEIGHT;
	Context context;
	Touch touch;
	float gravity, density;
	
	ArrayList<Entity> entities;
	
	public LetsBounce(Context context, Touch touch, float gravity) {
		this.context = context;
		this.touch = touch;
		this.gravity = gravity;
		density = context.getResources().getDisplayMetrics().density;
		entities = new ArrayList<Entity>();
		entities.add(new Falling(this, 50, 50, 64, 64, 0, 0, BitmapFactory.decodeResource(context.getResources(), R.drawable.red)));
		SCREEN_WIDTH = context.getResources().getDisplayMetrics().widthPixels;
		SCREEN_HEIGHT = context.getResources().getDisplayMetrics().heightPixels;
	}
	
	public void process() {
		for(Entity e : entities)
			e.process();
	}
}
