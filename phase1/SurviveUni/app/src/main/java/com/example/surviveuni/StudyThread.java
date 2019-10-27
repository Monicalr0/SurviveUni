package com.example.surviveuni;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

class StudyThread extends Thread {
    /**
     * where the study room view are drawn
     */
    private StudyRoomView view;

    /**
     * the canvas container
     */
    private SurfaceHolder surfaceHolder;

    /**
     * Whether the thread is running.
     */
    private boolean isRunning;
    /**
     * The canvas on which to draw the fish tank.
     */
    public static Canvas canvas;
}

