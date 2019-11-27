package com.example.surviveuni.sleep;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.surviveuni.R;

/**
 * A sheep.
 */
class Sheep extends SleepGameItem {
    /**
     * This sheep's width.
     */
    private int sheepWidth;

    /**
     * This sheep's height.
     */
    private int sheepHeight;

    /**
     * Bitmap of sheep
     */
    private Bitmap sheepLeft, sheepRight, appearance;

    /**
     * Indicates whether this sheep is moving right.
     */
    private boolean goingRight;

    /**
     * Use for random movement up or down, turn around.
     */
    private double d;

    /**
     * Constructs a new sheep at the specified cursor location (x, y).
     *
     * @param x the x coordinate of the sheep.
     * @param y the y coordinate of the sheep.
     */
    Sheep(int x, int y, Resources res) {
        super(x, y);
        goingRight = true;
        sheepLeft = BitmapFactory.decodeResource(res, R.drawable.sheep_left);
        sheepRight = BitmapFactory.decodeResource(res, R.drawable.sheep_right);
        appearance = sheepRight;
        sheepWidth = sheepLeft.getWidth();
        sheepHeight = sheepLeft.getHeight();
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
    }

    /**
     * Turns this sheep around, causing it to reverse direction.
     */
    private void turnAround() {
        d = Math.random();
        if (d < 0.1) {
            if (goingRight) appearance = sheepLeft;
            else appearance = sheepRight;
            goingRight = !goingRight;
        }
    }

    /**
     * Move Height / 50 steps to the right or left in the direction I'm going. turn around if bump into a wall.
     */
    private void moveRightLeft(int Width) {
        if (goingRight) {
            if (getX() + sheepWidth >= Width) {
                appearance = sheepLeft;
                goingRight = !goingRight;
            } else setX(getX() + Width / 50);
        } else {
            if (getX() <= 0) {
                appearance = sheepRight;
                goingRight = !goingRight;
            } else setX(getX() - Width / 50);
        }
    }

    /**
     * Figure out whether to move up or down Height / 50 steps, or neither.
     */
    private void moveUpDown(int Height) {
        d = Math.random();
        if (d < 0.2 && getY() + sheepHeight <= Height - Height / 50) setY(getY() + Height / 50);
        else if (d > 0.8 && getY() >= 15) setY(getY() - Height / 50);
    }
}
