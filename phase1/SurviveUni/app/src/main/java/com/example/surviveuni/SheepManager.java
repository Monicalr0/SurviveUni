package com.example.surviveuni;

import android.content.res.Resources;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class SheepManager {

  /** The width of Sheep. */
  private int gridWidth;
  /** The height of Sheep. */
  private int gridHeight;
  /** The list of sheep created* */
  private List<com.example.surviveuni.Sheep> SheepList = new ArrayList<>();

  private Resources res;

  SheepManager(int height, int width, Resources res) {
    gridHeight = height;
    gridWidth = width;
    this.res = res;
  }

  void draw(Canvas canvas) {
    for (com.example.surviveuni.Sheep sheep : SheepList) {
      sheep.draw(canvas);
    }
  }

  void update() {
    for (com.example.surviveuni.Sheep sheep : SheepList) {
      sheep.move(gridHeight, gridWidth);
    }
  }

  void createSheep() {
    int SheepNum = (int) (Math.random() * 15);
    for (int i = 0; i < SheepNum; i++) {
      int x = (int) (Math.random() * (gridWidth - 5)) + 5;
      int y = (int) (Math.random() * (gridHeight - 5)) + 5;
      SheepList.add(new com.example.surviveuni.Sheep(x, y, res));
    }
  }
}
