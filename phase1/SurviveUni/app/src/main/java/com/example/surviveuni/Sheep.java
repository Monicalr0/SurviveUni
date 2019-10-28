package com.example.surviveuni;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/** A sheep. */
class Sheep {

  /** This sheep's first coordinate. */
  private int x;
  /** This sheep's second coordinate. */
  private int y;

  private Bitmap sheep_left, sheep_right, appearance;

  /** Indicates whether this sheep is moving right. */
  private boolean goingRight;

  /** Use for random movement up or down, turn around. */
  private double d;

  private Paint paint = new Paint();
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
  }

  void draw(Canvas canvas) {
    canvas.drawBitmap(appearance, x , y, paint);
  }

  /** Turns this sheep around, causing it to reverse direction. */
  private void turnAround() {
    d = Math.random();
    if (d < 0.1) {
      if (goingRight) appearance = sheep_left;
      else appearance = sheep_right;

      goingRight = !goingRight;
    }
  }

  /** Causes this sheep to move, change the coordinates. */
  void move(int Height, int Width) {
    turnAround();
    moveRightLeft(Width);
    moveUpDown(Height);
  }

  /**
   * Move one spot to the right or left in the direction I'm going. turn around if bump into a wall.
   */
  private void moveRightLeft(int Width) {
    if (goingRight) {
      if (x >= Width - 5) turnAround();
      else x++;
    } else {
      if (x <= 5) turnAround();
      else x--;
    }
  }

  /** Figure out whether to move up or down, or neither. */
  private void moveUpDown(int Height) {
    d = Math.random();
    if (d < 0.1 && y <= Height - 5) y++;
    else if (d < 0.2 && y >= 5) y--;
  }
}
