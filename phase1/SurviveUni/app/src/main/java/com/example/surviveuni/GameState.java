package com.example.surviveuni;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

class GameState implements Serializable{
    private int GPA;
    private int spirit;
    private int happiness;
    private int dayOfSurvival;

    GameState(){
        GPA=50;
        spirit=50;
        happiness=50;
        dayOfSurvival=0;
    }

    int getGPA() {
        return GPA;
    }

    int getSpirit() {
        return spirit;
    }

    int getHappiness() {
        return happiness;
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

    void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public void setDayOfSurvival(int dayOfSurvival) {
        this.dayOfSurvival = dayOfSurvival;
    }

    void changeGPA(int GPA) { this.GPA += GPA;}

    void changeSpirit(int sp) {this.spirit += sp;}

    void changeHappiness(int hp) {this.happiness += hp;}


    public int checkGameover(){

        if(getSpirit() == 0 | getHappiness() == 0 | getGPA() ==0){
            return 1;
        }
        return 0;
    }


}
