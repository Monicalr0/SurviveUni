package com.example.surviveuni;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceView;
import android.view.SurfaceHolder;


/**
 * The StudyRoom view.
 */
public class StudyRoomView extends SurfaceView implements SurfaceHolder.Callback{
    /**
     * Screen width.
     */
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    /**
     * Screen height.
     */
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    /**
     * The width of a character.
     */
    public static float charWidth;
    /**
     * The height of a character.
     */
    public static float charHeight;

    /**
     * The StudyRoom content.
     */
    public StudyEventManager eventManager;

    private StudyThread thread;

    public StudyRoomView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new StudyThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        // Figure out the size of a letter.
        Paint paintText = new Paint();
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        charWidth = paintText.measureText("a");
        charHeight = -paintText.ascent() + paintText.descent();

        // Use the letter size and screen height to determine the size of the fish tank.
        eventManager = new StudyEventManager(
                (int) (screenHeight / charHeight), (int) (screenWidth / charWidth));
        eventManager.createEvents();

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

    public void update(){
        eventManager.update();
    }

    public void draw(Canvas canvas){
        eventManager.draw(canvas);
    }






















}
