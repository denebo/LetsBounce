package com.example.letsbounce;

import android.graphics.Bitmap;

public class Button extends Entity {
	Clickable click;
	public Button(Scene scene, float x, float y, float width, float height, Bitmap bmap, Clickable click) {
		super(scene, x, y, width, height, bmap);
		this.click = click;
	}
	
	@Override
	public void touch() {
		click.action();
	}
}
