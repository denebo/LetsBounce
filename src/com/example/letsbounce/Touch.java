package com.example.letsbounce;

public class Touch {
	float x, y, bounce;
	int counter;
	boolean clicking; // used for synchronization between UI thread and Game thread
	
	public Touch() {
		x = 0;
		y = 0;
		counter = 0;
		bounce = 40.0f;
		clicking = false;
	}
	
	public boolean click() {
		if(counter == 1 && clicking) return true;
		return false;
	}
	
	public boolean isTouching(Entity entity) {
		if(x > entity.x && x < entity.x + entity.width && y > entity.y && y < entity.y + entity.height)
			return true;
		return false;
	}
}
