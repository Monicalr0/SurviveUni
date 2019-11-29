package com.example.surviveuni.sleep;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;

import com.example.surviveuni.R;

/**
 * The sleep game view.
 */
public class SheepView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener{

    /**
     * The sleep game contents.
     */
    public SheepManager sheepManager;

    /**
     * Game level that user selected
     */
    private String levelSelected;

    /**
     * The part of the program that manages time.
     */
    private SheepThread thread;

    /**
     * background image of the sleep game
     */
    private Bitmap background;

    /**
     * Create a new sleep game in the context environment.
     *
     * @param context the environment.
     */
    public SheepView(Context context, SheepManager sheepManager, String levelSelected) {
        super(context);
        getHolder().addCallback(this);
        thread = new SheepThread(getHolder(), this);
        setFocusable(true);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.sleep_background);
        this.levelSelected = levelSelected;
        this.sheepManager = sheepManager;
        setOnTouchListener(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        sheepManager.createItems(levelSelected, getResources());

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

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

    /**
     * Update all sheep.
     */
    public void update() {
        sheepManager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawBitmap(background, 0, 0, null);
            sheepManager.draw(canvas);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        sheepManager.onTouchEvent(x, y);
        return true;
    }
}

