package com.example.letsbounce;

public abstract class Clickable {
	Scene scene;
	public Clickable(Scene scene) {
		this.scene = scene;
	}
	public abstract void action();
}
