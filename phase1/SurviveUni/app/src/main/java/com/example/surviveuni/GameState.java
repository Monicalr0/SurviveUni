package com.example.surviveuni;

import java.io.Serializable;

class GameState implements Serializable {
    private int GPA;
    private int spirit;
    private int hapiness;
    private int dayOfSurvival;

    int getGPA() {
        return GPA;
    }

    int getSpirit() {
        return spirit;
    }

    int getHapiness() {
        return hapiness;
    }

    int getDayOfSurvival() {
        return dayOfSurvival;
    }

    void setGPA(int GPA) {
        this.GPA = GPA;
    }

    void setSpirit(int spirit) {
        this.spirit = spirit;
    }

    void setHapiness(int hapiness) {
        this.hapiness = hapiness;
    }

    public void setDayOfSurvival(int dayOfSurvival) {
        this.dayOfSurvival = dayOfSurvival;
    }

    void studyWork(){
        double d = Math.random();
        if(d < 0.05){
            GPA ++;
        }
    }
}
