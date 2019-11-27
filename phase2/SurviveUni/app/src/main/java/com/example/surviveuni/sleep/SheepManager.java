package com.example.surviveuni.sleep;

import android.content.res.Resources;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

class SheepManager {

    /**
     * The width of game screen.
     */
    private int screenWidth;
    /**
     * The height of game screen.
     */
    private int screenHeight;

    /**
     * The list of sleep items created*
     */
    private List<SleepGameItem> itemList = new ArrayList<>();

    private Resources res;

    SheepManager(int height, int width, Resources res) {
        screenHeight = height;
        screenWidth = width;
        this.res = res;
    }


    public List<SleepGameItem> getItemList() {
        return itemList;
    }

    public void addItems(List<SleepGameItem> itemToAdd) {
        this.itemList.addAll(itemToAdd);
    }

    public void removeItems(List<SleepGameItem> itemToRemove) {
        this.itemList.removeAll(itemToRemove);
    }

    public void draw(Canvas canvas) {
        for (SleepGameItem item : itemList) {
            item.draw(canvas);
        }
    }

    void update() {
        for (SleepGameItem item : itemList) {
            item.move(screenHeight, screenWidth);
        }
    }

    /**
     * Create SheepNum numbers of sheep, and other items
     */
    void createItems(int sheepNum) {
        for (int i = 0; i < sheepNum + 3; i++) {
            int x = (int) (Math.random() * (screenWidth - 10)) + 10;
            int y = (int) (Math.random() * (screenHeight - 10)) + 10;
            if (i < sheepNum)
                itemList.add(new Sheep(x, y, res));
            else
                itemList.add(new Wolf(x, y, res));
        }
    }
}
