package com.example.letsbounce;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.BitmapFactory;

public class LetsBounce {
	int SCREEN_WIDTH, SCREEN_HEIGHT;
	Context context;
	Touch touch;
	float density;
	
	ArrayList<Entity> entities;
	ArrayList<Scene> scenes;
	Scene scene;
	
	public LetsBounce(Context context, Touch touch) {
		this.context = context;
		this.touch = touch;
		density = context.getResources().getDisplayMetrics().density;
		SCREEN_WIDTH = context.getResources().getDisplayMetrics().widthPixels;
		SCREEN_HEIGHT = context.getResources().getDisplayMetrics().heightPixels;
		scenes = new ArrayList<Scene>();
		
		Scene testScene = new Scene(this);
		testScene.entities.add(new Entity(testScene, 50, 0, 64, 64, BitmapFactory.decodeResource(context.getResources(), R.drawable.red)));
		
		scenes.add(0, new GameScene(this));
		scene = testScene;
	}
	
	public void process() {
		scene.process();
		touch.clicking = false; // for synchronization
	}
}
