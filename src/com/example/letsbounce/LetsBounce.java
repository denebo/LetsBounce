package com.example.letsbounce;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

public class LetsBounce {
	Context context;
	Touch touch;
	
	ArrayList<Entity> entities;
	
	public LetsBounce(Context context, Touch touch) {
		this.context = context;
		this.touch = touch;
		entities = new ArrayList<Entity>();
		entities.add(new Falling(50, 50, 64, 64, 0.1f, BitmapFactory.decodeResource(context.getResources(), R.drawable.red)));
	}
	
	public void process() {
		for(Entity e : entities) {
			if(touch.counter > 0) {
				if(touch.click()) {
					Log.d("ASDF", touch.x + ", " + touch.y);
					if(touch.isTouching(e))
						e.touch(touch);
				}
			}
			e.process();
		}
	}
}
