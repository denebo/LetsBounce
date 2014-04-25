package com.example.letsbounce;
import java.util.ArrayList;

public class Scene {
	ArrayList<Entity> entities;
	LetsBounce game;
	
	public Scene(LetsBounce game) {
		entities = new ArrayList<Entity>();
		this.game = game;
		initialize();
	}
	
	public void initialize() {
		// Initialize individual scene specific components
	}
	
	public void process() {
		for(Entity e : entities)
			e.process();
	}
	
	public void touched(Entity e) {
		// Entity e was touched
	}
}
