package com.example.surviveuni;

import android.graphics.Canvas;
import java.util.ArrayList;
import java.util.List;

class StudyEventManager {
    /**
     * All events
     */
    List<SuperEvent> events;

    /**
     * room height
     */
    private int roomHeight;

    /**
     * room width
     */
    private int roomWidth;

    /**
     * The study room with height rows and width columns
     */
    public StudyEventManager(int roomHeight, int roomWidth) {
        this.roomHeight = roomHeight;
        this.roomWidth = roomWidth;
        events = new ArrayList<>();
    }

     void draw(Canvas canvas){
        int i = 0;
        while(i < events.size())
        {
            i.draw(canvas);
            i++;
        }
     }

     void update(){
         int i = 0;
         while(i < events.size())
         {
             i.update();
             i++;
         }
     }

     void createEvents(){}
}
