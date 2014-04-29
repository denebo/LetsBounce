package com.example.letsbounce;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;

public class LetsBounce {
	SharedPreferences pref;
	int SCREEN_WIDTH, SCREEN_HEIGHT;
	Context context;
	Touch touch;
	float density;
	
	ArrayList<Scene> scenes;
	Scene activeScene, gameOver;
	
	public LetsBounce(Context context, Touch touch) {
		this.context = context;
		this.touch = touch;
		density = context.getResources().getDisplayMetrics().density;
		SCREEN_WIDTH = context.getResources().getDisplayMetrics().widthPixels;
		SCREEN_HEIGHT = context.getResources().getDisplayMetrics().heightPixels;
		scenes = new ArrayList<Scene>();
		
		Scene mainMenu = new Scene(this);
		mainMenu.entities.add(new Entity(mainMenu, 0, 0, 300, 200,
				BitmapFactory.decodeResource(context.getResources(), R.drawable.logo)));
		mainMenu.entities.add(new Button(mainMenu, 75, 370, 200, 100, 
				BitmapFactory.decodeResource(context.getResources(), R.drawable.play), 
				new Clickable(mainMenu) { public void action() { scene.game.activeScene = new GameScene(scene.game); } }
				));
		
		activeScene = mainMenu;
		pref = context.getSharedPreferences("com.example.letsbounce", context.MODE_PRIVATE);
	}
	
	public void process() {
		activeScene.process();
		touch.clicking = false; // for synchronization
	}
}
