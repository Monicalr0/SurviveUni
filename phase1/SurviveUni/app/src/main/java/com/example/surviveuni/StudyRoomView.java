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
    public EventManager eventManager;

    private StudyThread thread;

    public StudyRoomView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new StudyThread(getHolder(), this);
        setFocusable(true);
    }

    
}
