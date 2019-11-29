package com.example.surviveuni.sleep;

import android.content.res.Resources;
import android.graphics.Canvas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class SheepManager implements Serializable {

    /**
     * The width of game screen.
     */
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    /**
     * The height of game screen.
     */
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    /**
     * Number of Sheep to be created
     */
    private int sheepNum;

    /**
     * The list of sleep items created*
     */
    private List<SleepGameItem> itemList;

    SheepManager() {
        this.itemList = new ArrayList<>();
    }

    public int getSheepNum(){
        return sheepNum;
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
    void createItems(String levelSelected, Resources res) {
        setSheepNum(levelSelected);
        for (int i = 0; i < sheepNum + 3; i++) {
            int x = (int) (Math.random() * (screenWidth - 15)) + 10;
            int y = (int) (Math.random() * (screenHeight - 15)) + 10;
            if (i < sheepNum)
                itemList.add(new Sheep(x, y, res));
            else
                itemList.add(new Wolf(x, y, res));
        }
    }

    /**
     * set the number Sheep to be created according to the user selected level
     */
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
}
