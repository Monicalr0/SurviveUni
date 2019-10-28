package com.example.surviveuni;

import android.content.res.Resources;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class SheepManager {

  /** The width of game screen. */
  private int screenWidth;
  /** The height of gamge screen. */
  private int screenHeight;

  /** The list of sheep created* */
  private List<Sheep> SheepList = new ArrayList<>();

  private Resources res;

  SheepManager(int height, int width, Resources res) {
    screenHeight = height;
    screenWidth = width;
    this.res = res;
  }

  void draw(Canvas canvas) {
    for (com.example.surviveuni.Sheep sheep : SheepList) {
      sheep.draw(canvas);
    }
  }

  void update() {
    for (com.example.surviveuni.Sheep sheep : SheepList) {
      sheep.move(screenHeight, screenWidth);
    }
  }

  /** Create 5 - 15 numbers of sheep */
  void createSheep() {
    int SheepNum = (int) (Math.random() * 10) + 5;
    for (int i = 0; i < SheepNum; i++) {
      int x = (int) (Math.random() * (screenWidth - 10)) + 10;
      int y = (int) (Math.random() * (screenHeight - 10)) + 10;
      SheepList.add(new Sheep(x, y, res));
    }
  }
}
