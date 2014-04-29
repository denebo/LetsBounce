package com.example.letsbounce;

import java.util.Random;

import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.util.Log;

public class GameScene extends Scene {
	public float gravity, mYCap, mYPad, mXCap;
	Label scoreLabel, livesLabel;
	public int score, spawnRate;
	int lvlHealth, lives;
	boolean spawned, gameOver;
	long startTime, elapsedTime;
	
	public GameScene(LetsBounce game) {
		super(game);
	}
	
	@Override
	public void initialize() {
		gravity = 0.15f;
		mYCap = 15.0f;
		mYPad = 5.0f;
		mXCap = 8.0f;
		lives = 3;
		score = 0;
		spawned = false;
		spawnRate = 30;
		startTime = System.currentTimeMillis();
		lvlHealth = 0;
		scoreLabel = new Label(this, 10 / game.density + 60, game.SCREEN_HEIGHT / game.density - 5, "Score: " + score, 100.0f);
		livesLabel = new Label(this, 10 / game.density + 60, game.SCREEN_HEIGHT / game.density - 40, "Lives: " + lives, 100.0f);
		livesLabel.r = 255;
		livesLabel.g = 50;
		livesLabel.b = 50;
		entities.add(scoreLabel);
		entities.add(livesLabel);
		gameOver = false;
	}
	
	@Override
	public void process() {
		super.process();
		// check for collisions between falling objects and make them bounce
		// at respective angles
		for(int i = 0; i < entities.size(); i++) {
			Entity e_ = entities.get(i);
			if(e_ instanceof FObject) {
				FObject e = (FObject)entities.get(i);
				for (int j = i; j < entities.size(); j++) {
					Entity ec_ = entities.get(j);
					if(ec_ instanceof FObject) {
						FObject ec = (FObject)entities.get(j);
						if(e != ec && (!e.spawning && !ec.spawning)) {
							float dX = e.getCenterX() - ec.getCenterX();
							float dY = e.getCenterY() - ec.getCenterY();
							double hyp = Math.sqrt(dX * dX + dY * dY);
							
							if(Math.abs(hyp) < e.width / 2 + ec.width / 2) {
								double angle = Math.atan2(dY, dX);
								float fX = (float)Math.cos(angle) * ((e.bounce / 2 + ec.bounce / 2) / 2);
								float fY = (float)Math.sin(angle) * ((e.bounce / 2 + ec.bounce / 2) / 2);
			
								e.mX += fX;
								e.mY += fY;
								ec.mX -= fX;
								ec.mY -= fY;
							}
						}
					}
				}
			}
		}
		
		// remove objects that fall below screen
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if(e.y > game.SCREEN_HEIGHT) {
				if(e.width != 96 * game.density && e.y < game.SCREEN_HEIGHT + 100)
					lives -= 1;
				entities.remove(i);
			}
		}
		
		if(!gameOver) {
			// Spawning and levels
			elapsedTime = System.currentTimeMillis() - startTime;
			float xSpawn = new Random().nextInt((game.SCREEN_WIDTH - 96) / Math.round(game.density));
			if(elapsedTime > spawnRate * 1000 || entities.size() == 2) {
				score++;
				
				if(score < 15) 
					lvlHealth = score + 1;
				if(score <= 6) {
					spawnRate = 10 - score;
				}
				
				if(score % 5 == 0 && score != 1) {
					entities.add(new FDestroyable(this, xSpawn, -96.0f, 96, 96, 15.0f, 0.35f, BitmapFactory.decodeResource(game.context.getResources(),
							R.drawable.green), 3));
				} else {
					entities.add(new FDestroyable(this, xSpawn, -64.0f, 64, 64, 15.0f, 0, BitmapFactory.decodeResource(game.context.getResources(),
							R.drawable.red), lvlHealth));
				}
				
				startTime = System.currentTimeMillis();
			}
			
			scoreLabel.label = "Score: " + score;
			livesLabel.label = "Lives: " + lives;
		}
		if(lives <= 0) {
			if(!gameOver) {
				entities.add(new Label(this, game.SCREEN_WIDTH / 2 / game.density, 100, "Game Over", 200.0f));
				entities.add(new Button(this, game.SCREEN_WIDTH / 2 / game.density - 100, 200, 200, 100, 
						BitmapFactory.decodeResource(game.context.getResources(), R.drawable.playagain), 
						new Clickable(this) { public void action() { scene.game.activeScene = new GameScene(scene.game); } }
						));
				int highScore = game.pref.getInt("score", 1);
				if(score > highScore) {
					highScore = score;
					SharedPreferences.Editor editor = game.pref.edit().putInt("score", score);
					editor.commit();
				}
				highScore = game.pref.getInt("score", 200);
				
				entities.add(new Label(this, game.SCREEN_WIDTH / 2 / game.density, 185, "Highscore: " + highScore, 100.0f));
			}
			gameOver = true;
		}		
	}
}
