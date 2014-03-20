package com.example.letsbounce;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
	boolean mRun;
	Canvas mcanvas;
	Context context;
	SurfaceHolder surfaceHolder;
	SurfacePanel msurfacePanel;
	
	public MainThread(SurfaceHolder sholder, Context ctxt, SurfacePanel spanel) {
		surfaceHolder = sholder;
		context = ctxt;
		mRun = false;
		msurfacePanel = spanel;
	}
	
	void setRunning(boolean bRun) {
		mRun = bRun;
	}

  @Override
  public void run()  {
      super.run();
      while(mRun) {
          mcanvas = surfaceHolder.lockCanvas();
          if(mcanvas != null) {
        	msurfacePanel.mGame.process(); // game logic
            msurfacePanel.doDraw(mcanvas);
            surfaceHolder.unlockCanvasAndPost(mcanvas);
          }
      }
      
      try {
    	  wait();
      } catch (Exception e) {
    	  Log.d("ASDF", e.getMessage());
      }
  }
}
