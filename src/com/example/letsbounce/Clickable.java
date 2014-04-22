package com.example.letsbounce;

public abstract class Clickable {
	LetsBounce game;
	public Clickable(LetsBounce game) {
		this.game = game;
	}
	public abstract void action();
}
