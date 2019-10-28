package com.example.surviveuni;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

/** The sleep game view. */
public class SheepView extends SurfaceView implements SurfaceHolder.Callback {

  /** Screen width. */
  private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
  /** Screen height. */
  private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

  /** The sleep game contents. */
  public SheepManager sheepManager;

  /** The part of the program that manages time. */
  private SheepThread thread;

  /**
   * Create a new sleep game in the context environment.
   *
   * @param context the environment.
   */
  public SheepView(Context context) {
    super(context);
    getHolder().addCallback(this);
    thread = new SheepThread(getHolder(), this);
    setFocusable(true);
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    sheepManager = new SheepManager(screenHeight, screenWidth, getResources());
    sheepManager.createSheep();

    thread.setRunning(true);
    thread.start();
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    boolean retry = true;
    while (retry) {
      try {
        thread.setRunning(false);
        thread.join();

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      retry = false;
    }
  }

  /** Update all sheep. */
  public void update() {
    sheepManager.update();
  }

  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    if (canvas != null) {
      sheepManager.draw(canvas);
    }
  }
}
