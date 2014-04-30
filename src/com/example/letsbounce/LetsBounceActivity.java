package com.example.letsbounce;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class LetsBounceActivity extends AbstractActivity{
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	player = MediaPlayer.create(LetsBounceActivity.this, R.raw.bounce); 

        player.setLooping(true); // Set looping 
        player.setVolume(100,100); 
        player.start(); 

    	
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // remove title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_surface);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.surface, menu);
        return true;
    }
    
    public void onDestory() {
    	super.onDestroy();
    	player.release();
    }
    
    public void onPause() {
    	super.onPause();
    	player.pause();
    }
    public void onResume() {
    	super.onResume();
    	player.start();
    }
}
