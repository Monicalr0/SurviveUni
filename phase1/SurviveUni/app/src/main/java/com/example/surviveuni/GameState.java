package com.example.surviveuni;

import java.io.Serializable;

class GameState implements Serializable {
    private int GPA;
    private int spirit;
    private int hapiness;
    private int dayOfSurvival;
    private int a;
    private int b;

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

    void changeGPA(int GPA) { this.GPA += GPA;}

    void changeSpirit(int sp) {this.spirit += sp;}

    void changeHapiness(int hp) {this.hapiness += hp;}

    int getFirstNum(){
        a = ((int)(Math.random()*1000));
        return a;
    }

    int getSecondNum(){
        b = ((int)(Math.random()*1000));
        return b;
    }

    boolean studyCheck(int ans) {return ans == a+b;}
}
