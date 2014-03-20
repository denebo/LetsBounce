package com.example.letsbounce;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfacePanel extends SurfaceView implements SurfaceHolder.Callback {
	  Context context;
	  MainThread drawThread;
	  LetsBounce mGame;
	  Touch touch;
	  int touchCounter;

	public SurfacePanel(Context ctxt, AttributeSet attrSet) {
		super(ctxt, attrSet);
		context = ctxt;	
		touch = new Touch();
		touchCounter = 0;
		mGame = new LetsBounce(ctxt, touch);
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		drawThread.setRunning(false);
		boolean retry = true;
		while(retry) {
			try {
				drawThread.join();
				retry = false;
			} catch (Exception e) {
				Log.v("Exception Occured", e.getMessage());
			}
		}
        
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, 
			int format,
			int width,
			int height) {
		Log.d("ASDF","changed");
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
	    drawThread = new MainThread(holder, context, this);		
	    drawThread.setRunning(true);
	    drawThread.start();
	}
	
	void doDraw(Canvas canvas) {
		canvas.drawColor(Color.CYAN);
		canvas.drawBitmap(mGame.ball.getBitmap(), mGame.ball.getX(), mGame.ball.getY(), null);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		touch.clicking = true;
		touchCounter++;
		
		if(e.getAction() == MotionEvent.ACTION_UP)
			touchCounter = 0;
		
		touch.counter = touchCounter;
		touch.x = e.getX();
		touch.y = e.getY();
		
	    return true;
	}
}
