package com.example.surviveuni.sleep;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Hacky way to manage threading and updates.
 */
public class SheepThread extends Thread {

    /**
     * Where the sheep are drawn.
     */
    private SheepView sheepView;
    /**
     * The canvas container.
     */
    private SurfaceHolder surfaceHolder;
    /**
     * Whether the thread is running.
     */
    private boolean isRunning;
    /**
     * The canvas on which to draw the sleep game
     */
    public static Canvas canvas;

    /**
     * Construct the thread.
     *
     * @param surfaceHolder the canvas container.
     * @param view          where the sheep are drawn.
     */
    public SheepThread(SurfaceHolder surfaceHolder, SheepView view) {
        this.surfaceHolder = surfaceHolder;
        this.sheepView = view;
    }

    @Override
    public void run() {
        while (isRunning) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.sheepView.update();
                    this.sheepView.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                this.sleep(30);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}
