package com.example.surviveuni.sleep;

import android.graphics.Canvas;

abstract class SleepGameItem {

    /**
     * This SleepGameItem's first coordinate.
     */
    private int x;
    /**
     * This SleepGameItem's second coordinate.
     */
    private int y;

    /**
     * Constructs a SleepGameItem at the specified cursor location (x, y).
     *
     * @param x_cor the x coordinate of the item.
     * @param y_cor the y coordinate of the item.
     */
    SleepGameItem(int x_cor, int y_cor) {
        x = x_cor;
        y = y_cor;

    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void setX(int new_x) {
        x = new_x;
    }

    void setY(int new_y) {
        y = new_y;
    }

    /**
     * Causes this item to move, change its corresponding coordinates.
     */
    abstract void move(int ScreenHeight, int ScreenWidth);

    /**
     * Draws this item.
     *
     * @param canvas the graphics context in which to draw this item.
     */
    abstract void draw(Canvas canvas);
}
