package com.example.letsbounce;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

public class LetsBounce {
	Context context;
	Falling ball;
	Touch touch;
	
	public LetsBounce(Context context, Touch touch) {
		this.context = context;
		ball = new Falling(50, 50, 64, 64, 0.5f, BitmapFactory.decodeResource(context.getResources(), R.drawable.red));
		this.touch = touch;
	}
	
	public void process() {
		ball.process();
		
		if(touch.click()) {
			Log.d("ASDF", "Clicked " + touch.x + ", " + touch.y + "!");
		}
	}
}
