package com.example.letsbounce;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

public class LetsBounce {
	int SCREEN_WIDTH, SCREEN_HEIGHT;
	Context context;
	Touch touch;
	float density;
	
	ArrayList<Entity> entities;
	ArrayList<Scene> scenes;
	Scene activeScene;
	
	public LetsBounce(Context context, Touch touch) {
		this.context = context;
		this.touch = touch;
		density = context.getResources().getDisplayMetrics().density;
		SCREEN_WIDTH = context.getResources().getDisplayMetrics().widthPixels;
		SCREEN_HEIGHT = context.getResources().getDisplayMetrics().heightPixels;
		Log.d("ASDF", density + "");
		scenes = new ArrayList<Scene>();
		
		Scene mainMenu = new Scene(this);
		mainMenu.entities.add(new Button(mainMenu, (200) / density, (10) / density, 200, 100, 
				BitmapFactory.decodeResource(context.getResources(), R.drawable.play), 
				new Clickable(mainMenu) { public void action() { scene.game.activeScene = new GameScene(scene.game); } }
				));
		
		scenes.add(0, new GameScene(this));
		activeScene = mainMenu;
	}
	
	public void process() {
		activeScene.process();
		touch.clicking = false; // for synchronization
	}
}
