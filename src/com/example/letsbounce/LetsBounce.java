package com.example.letsbounce;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

public class LetsBounce {
	Context context;
	Touch touch;
	float gravity, density;
	
	ArrayList<Entity> entities;
	
	public LetsBounce(Context context, Touch touch, float gravity, float density) {
		this.context = context;
		this.touch = touch;
		this.gravity = gravity;
		this.density = density;
		entities = new ArrayList<Entity>();
		entities.add(new Falling(this, 50 * density, 50 * density, 64 * density, 64 * density, 0, 0, BitmapFactory.decodeResource(context.getResources(), R.drawable.red)));
	}
	
	public void process() {
		for(Entity e : entities) {
			if(touch.counter > 0) {
				if(touch.click()) {
					Log.d("ASDF", touch.x + ", " + touch.y);
					if(touch.isTouching(e))
						e.touch();
				}
			}
			e.process();
		}
	}
}
