package com.example.letsbounce;

public class Label extends Entity {
	String label;
	boolean centered;
	int r, g, b;
	float size;
	
	public Label(Scene scene, float x, float y, String label, int size, boolean centered) {
		super(scene, x, y, 0, 0);
		this.label = label;
		this.size = size;
		this.centered = centered;
		r = 0;
		g = 0;
		b = 0;
	}
}
