package com.example.letsbounce;

import android.content.Context;
import android.graphics.BitmapFactory;

public class LetsBounce {
	Context context;
	Falling ball;
	
	
	public LetsBounce(Context context) {
		this.context = context;
		ball = new Falling(50, 50, 64, 64, 0.5f, BitmapFactory.decodeResource(context.getResources(), R.drawable.red));
	}
	
	public void process() {
		ball.process();
			
	}
}
