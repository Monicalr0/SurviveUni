package com.example.surviveuni.sleep;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.surviveuni.R;

import java.util.ArrayList;
import java.util.List;

public class Wolf extends SleepGameItem {
    /**
     * This Wolf's width.
     */
    private int wolfWidth;

    /**
     * This Wolf's height.
     */
    private int wolfHeight;

    /**
     * Bitmap of Wolf
     */
    private Bitmap wolfLeft, wolfRight, appearance;

    /**
     * Indicates whether this Wolf is moving right.
     */
    private boolean goingRight;

    /**
     * Use for random movement up or down, turn around.
     */
    private double d;

    /**
     * Constructs a new Wolf at the specified cursor location (x, y).
     *
     * @param x the x coordinate of the Wolf.
     * @param y the y coordinate of the Wolf.
     */
    Wolf(int x, int y, Resources res) {
        super(x, y);
        goingRight = true;
//        wolfLeft = getResizedBitmap(BitmapFactory.decodeResource(res, R.drawable.wolf_left), 200, 200);
//        wolfRight = getResizedBitmap(BitmapFactory.decodeResource(res, R.drawable.wolf_right), 200, 200);
        appearance = wolfRight;
        wolfWidth = wolfLeft.getWidth();
        wolfHeight = wolfRight.getHeight();
    }

    @Override
    void draw(Canvas canvas) {
        canvas.drawBitmap(appearance, getX(), getY(), null);
    }

    /**
     * Causes this sheep to move, change its corresponding coordinates.
     */
    @Override
    void move(int ScreenHeight, int ScreenWidth) {
        turnAround();
        moveRightLeft(ScreenWidth);
        moveUpDown(ScreenHeight);
//        eat();
    }

    /**
     * resize the bibmap
     */
    public Bitmap getResizedBitmap(Bitmap image, int bitmapWidth, int bitmapHeight) {
        return Bitmap.createScaledBitmap(image, bitmapWidth, bitmapHeight, true);
    }

    /**
     * Turns this sheep around, causing it to reverse direction.
     */
    private void turnAround() {
        d = Math.random();
        if (d < 0.1) {
            if (goingRight) appearance = wolfLeft;
            else appearance = wolfRight;
            goingRight = !goingRight;
        }
    }

    /**
     * Move Height / 50 steps to the right or left in the direction I'm going. turn around if bump into a wall.
     */
    private void moveRightLeft(int Width) {
        if (goingRight) {
            if (getX() + wolfWidth >= Width) turnAround();
            else setX(getX() + Width / 50);
        } else {
            if (getX() <= 0) turnAround();
            else setX(getX() - Width / 50);
        }
    }

    /**
     * Figure out whether to move up or down Height / 50 steps, or neither.
     */
    private void moveUpDown(int Height) {
        d = Math.random();
        if (d < 0.1 && getY() + wolfHeight <= Height - Height / 50) setY(getY() + Height / 50);
        else if (d > 0.8 && getY() >= 15) setY(getY() - Height / 50);
    }

    /**
     * Wolf eats nearby items.
     */
    private void eat(List<SleepGameItem> sleepGameItems) {
        List<SleepGameItem> eatenItem = new ArrayList<>();
        for (SleepGameItem item : sleepGameItems) {
            if (!(item instanceof Wolf)) {
                // check for the distance between wolf and other items
                double distance =
                        Math.hypot(Math.abs(item.getX() - this.getX()), Math.abs(item.getY() - this.getY()));
                if (distance <= 5) {
                    eatenItem.add(item);
                    this.setX(item.getX());
                    this.setY(item.getY());
                }
            }
        }
    }
}
