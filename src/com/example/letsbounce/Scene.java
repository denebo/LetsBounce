package com.example.letsbounce;
import java.util.ArrayList;

public class Scene {
	ArrayList<Entity> entities;
	LetsBounce game;
	
	public Scene(LetsBounce game) {
		entities = new ArrayList<Entity>();
		this.game = game;
	}
	
	public void process() {
		for(Entity e : entities)
			e.process();
	}
}
