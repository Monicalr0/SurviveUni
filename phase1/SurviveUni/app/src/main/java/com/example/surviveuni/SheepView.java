package com.example.surviveuni;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

/** The fish tank view. */
public class SheepView extends SurfaceView implements SurfaceHolder.Callback {

  /** Screen width. */
  private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
  /** Screen height. */
  private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

  /** The width of a character. */
  public static float charWidth;
  /** The height of a character. */
  public static float charHeight;

  /** The fish tank contents. */
  public com.example.surviveuni.SheepManager sheepManager;
  /** The part of the program that manages time. */
  private SheepThread thread;

  private Resources res;
  /**
   * Create a new sheep game in the context environment.
   *
   * @param context the environment.
   */
  public SheepView(Context context, Resources res) {
    super(context);
    getHolder().addCallback(this);
    thread = new SheepThread(getHolder(), this);
    setFocusable(true);
    this.res = res;
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {

    // Figure out the size of a letter.
    Paint paintText = new Paint();
    paintText.setTextSize(36);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    charWidth = paintText.measureText("W");
    charHeight = -paintText.ascent() + paintText.descent();

    sheepManager =
            new com.example.surviveuni.SheepManager((int) (screenHeight / charHeight), (int) (screenWidth / charWidth), res);
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
