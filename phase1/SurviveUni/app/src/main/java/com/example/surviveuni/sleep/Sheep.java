package com.example.surviveuni.sleep;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.surviveuni.R;

/**
 * A sheep.
 */
class Sheep {

    /**
     * This sheep's first coordinate.
     */
    private int x;

    /**
     * This sheep's second coordinate.
     */
    private int y;

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
    private Bitmap sheep_left, sheep_right, appearance;

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
        this.x = x;
        this.y = y;
        goingRight = true;
        sheep_left = BitmapFactory.decodeResource(res, R.drawable.sheep_left);
        sheep_right = BitmapFactory.decodeResource(res, R.drawable.sheep_right);
        sheepWidth = sheep_left.getWidth();
        sheepHeight = sheep_left.getHeight();
    }

    void draw(Canvas canvas) {
        canvas.drawBitmap(appearance, x, y, null);
    }

    /**
     * Turns this sheep around, causing it to reverse direction.
     */
    private void turnAround() {
        d = Math.random();
        if (d < 0.1) {
            if (goingRight) appearance = sheep_left;
            else appearance = sheep_right;
            goingRight = !goingRight;
        }
    }

    /**
     * Causes this sheep to move, change its corresponding coordinates.
     */
    void move(int Height, int Width) {
        turnAround();
        moveRightLeft(Width);
        moveUpDown(Height);
    }

    /**
     * Move Height / 50 steps to the right or left in the direction I'm going. turn around if bump into a wall.
     */
    private void moveRightLeft(int Width) {
        if (goingRight) {
            if (x + sheepWidth >= Width) turnAround();
            else x += Width / 50;
        } else {
            if (x <= 0) turnAround();
            else x -= Width / 50;
        }
    }

    /**
     * Figure out whether to move up or down Height / 50 steps, or neither.
     */
    private void moveUpDown(int Height) {
        d = Math.random();
        if (d < 0.1 && y + sheepHeight <= Height - Height / 50) y += Height / 50;
        else if (d < 0.2 && y >= 10) y -= Height / 50;
    }
}
