package com.example.letsbounce;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
	    drawThread = new MainThread(holder, context, this);		
	    drawThread.setRunning(true);
	    drawThread.start();
	}
	
	void doDraw(Canvas canvas) {
		canvas.drawColor(Color.CYAN);
		Paint paint = new Paint();
		
		for(Entity e : mGame.activeScene.entities){
			if(e.bmap != null) canvas.drawBitmap(e.bmap, e.x, e.y, null);
			if(e instanceof Label) {
				Label label = (Label)e;
				paint.setARGB(255, label.r, label.g, label.b);
				paint.setTextSize(getResources().getDimension((int)label.size) / mGame.density);
				float labelX = label.x;
				if(label.centered)
					labelX = label.x - paint.measureText(label.label) / 2;
				canvas.drawText(label.label, labelX, label.y - label.size / 2, paint);
			} else if(e instanceof FDestroyable) {
				FDestroyable e_ = (FDestroyable)e;
				paint.setARGB(255, 255, 255, 255);
				paint.setTextSize(e_.width);
				float healthY = e.y + e.height * 65/80;
				if(e_.health >= 10) {
					paint.setTextSize(e_.width / 2);
					healthY -= e.height / 4;
				}
				canvas.drawText(e_.health + "", e.x + e.width * 12/60, healthY, paint);
			}
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		touch.clicking = true; // for synchronization
		touchCounter++;
		
		if(e.getAction() == MotionEvent.ACTION_UP)
			touchCounter = 0;
		
		touch.counter = touchCounter;
		touch.x = e.getRawX();
		touch.y = e.getRawY();
		
	    return true;
	}
}
