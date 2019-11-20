package com.example.surviveuni.sleep;

import android.content.res.Resources;
import android.graphics.Canvas;

import com.example.surviveuni.sleep.Sheep;

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
     * The list of sheep created*
     */
    private List<Sheep> SheepList = new ArrayList<>();

    private Resources res;

    SheepManager(int height, int width, Resources res) {
        screenHeight = height;
        screenWidth = width;
        this.res = res;
    }

    void draw(Canvas canvas) {
        for (Sheep sheep : SheepList) {
            sheep.draw(canvas);
        }
    }

    void update() {
        for (Sheep sheep : SheepList) {
            sheep.move(screenHeight, screenWidth);
        }
    }

    /**
     * Create SheepNum numbers of sheep
     */
    void createSheep(int SheepNum) {
        for (int i = 0; i < SheepNum; i++) {
            int x = (int) (Math.random() * (screenWidth - 10)) + 10;
            int y = (int) (Math.random() * (screenHeight - 10)) + 10;
            SheepList.add(new Sheep(x, y, res));
        }
    }
}
