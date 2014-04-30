package com.example.letsbounce;

public class Label extends Entity {
	String label;
	boolean centered;
	int r, g, b;
	int size;
	
	public Label(Scene scene, float x, float y, String label, int size) {
		super(scene, x, y, 0, 0);
		this.label = label;
		this.size = size;
		r = 0;
		g = 0;
		b = 0;
	}
}
