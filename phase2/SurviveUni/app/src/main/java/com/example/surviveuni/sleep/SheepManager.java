package com.example.surviveuni.sleep;

import android.content.res.Resources;
import android.graphics.Canvas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class SheepManager implements Serializable {

  /** The width of game screen. */
  private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
  /** The height of game screen. */
  private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

  /** Number of Sheep to be created */
  private int sheepNum;

  /** Number of Wolves tapped */
  private int touchedWolfNumber;

  /** The list of sleep items created* */
  private List<SleepGameItem> itemList;

  SheepManager() {
    this.itemList = new ArrayList<>();
    this.touchedWolfNumber = 0;
  }

  int getSheepNum() {
    return sheepNum;
  }

  int getTouchedWolfNumber() {
    return touchedWolfNumber;
  }

  void draw(Canvas canvas) {
    for (SleepGameItem item : itemList) {
      item.draw(canvas);
    }
  }

  void update() {
    List<SleepGameItem> itemToRemove = new ArrayList<>();
    this.touchedWolfNumber = 0;
    for (SleepGameItem item : itemList) {
      item.move(screenHeight, screenWidth);
      if (item instanceof Wolf) {
        itemToRemove.addAll(((Wolf) item).eat(itemList));
        if (((Wolf) item).getTouched()) {
          touchedWolfNumber++;
        }
      }
    }
    itemList.removeAll(itemToRemove);
  }

  /** Create SheepNum numbers of sheep, and other items */
  void createItems(String levelSelected, Resources res) {
    setSheepNum(levelSelected);
    for (int i = 0; i < sheepNum + 3; i++) {
      int x = (int) (Math.random() * (screenWidth - 15)) + 10;
      int y = (int) (Math.random() * (screenHeight - 40)) + 10;
      if (i < sheepNum) itemList.add(new Sheep(x, y, res));
      else itemList.add(new Wolf(x, y, res));
    }
  }

  /** set the number Sheep to be created according to the user selected level */
  private void setSheepNum(String levelSelected) {
    switch (levelSelected) {
      case "HARD":
        sheepNum = (int) (Math.random() * 5) + 13;
        break;
      case "NORMAL":
        sheepNum = (int) (Math.random() * 5) + 8;
        break;
      case "EASY":
        sheepNum = (int) (Math.random() * 5) + 3;
        break;
    }
  }

  void onTouchEvent(int touchX, int touchY) {
    for (SleepGameItem item : itemList) {
      if (item instanceof Wolf) {
        ((Wolf) item).onTouchEvent(touchX, touchY);
      }
    }
  }
}
