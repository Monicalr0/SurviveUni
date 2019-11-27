package com.example.surviveuni.sleep;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.example.surviveuni.R;

/**
 * The sleep game view.
 */
public class SheepView extends SurfaceView implements SurfaceHolder.Callback {

    /**
     * Screen width.
     */
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    /**
     * Screen height.
     */
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    /**
     * The sleep game contents.
     */
    public SheepManager sheepManager;

    /**
     * Number of Sheep to be created
     */
    private int SheepNum;

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
    public SheepView(Context context, int SheepNum) {
        super(context);
        getHolder().addCallback(this);
        thread = new SheepThread(getHolder(), this);
        setFocusable(true);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.sleep_background);
        this.SheepNum = SheepNum;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        sheepManager = new SheepManager(screenHeight, screenWidth, getResources());
        sheepManager.createItems(SheepNum);

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
        canvas.drawBitmap(background, 0, 0, null);
        sheepManager.draw(canvas);
    }
}

