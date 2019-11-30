package com.example.surviveuni.data;

import java.io.Serializable;
import java.util.Observable;

public class GameState extends Observable implements Serializable {
    private int GPA;
    private int spirit;
    private int happiness;
    private int dayOfSurvival;

    public GameState() {
        GPA = 50;
        spirit = 50;
        happiness = 50;
        dayOfSurvival = 0;
    }

    /**
     * Return the GPA of this user
     *
     * @return GPA
     */
    public int getGPA() {
        return GPA;
    }

    /**
     * Return the Spirit of this user
     *
     * @return Spirit
     */
    public int getSpirit() {
        return spirit;
    }

    /**
     * Return the Happiness of this user
     *
     * @return Happiness
     */
    public int getHappiness() {
        return happiness;
    }

    /**
     * Return the dayOfSurvival of this user
     *
     * @return number of days survived
     */
    public int getDayOfSurvival() {
        return dayOfSurvival;
    }

    /**
     * Set the GPA of this user
     *
     * @param GPA GPA to set
     */
    public void setGPA(int GPA) {
        this.GPA = GPA;
    }

    /**
     * Set the spirit of this user
     *
     * @param spirit spirit to set
     */
    public void setSpirit(int spirit) {
        this.spirit = spirit;
    }

    /**
     * Set the happiness of this user
     *
     * @param happiness happiness to set
     */
    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    /**
     * Set the dayOfSurvival of this user
     *
     * @param dayOfSurvival dayOfSurvival to set
     */
    public void setDayOfSurvival(int dayOfSurvival) {
        this.dayOfSurvival = dayOfSurvival;
    }

    /**
     * Change the GPA of this user
     *
     * @param GPA increase/decrease by GPA
     */
    public void changeGPA(int GPA) {
        this.GPA += GPA;
    }

    /**
     * Change the spirit of this user
     *
     * @param sp increase/decrease by sp
     */
    public void changeSpirit(int sp) {
        this.spirit += sp;
    }

    /**
     * Change the GPA of this user
     *
     * @param hp increase/decrease by hp
     */
    public void changeHappiness(int hp) {
        this.happiness += hp;
    }

    /**
     * Increase dayOfSurvival
     */
    public void updateDay() {
        dayOfSurvival++;
    }

    /**
     * if one of GPA, Spirit, Happiness is 0, the game ends
     *
     * @return 1 if game ends, 0 if game continues
     */
    public int checkGameover() {

        if (getSpirit() <= 0 | getHappiness() <= 0 | getGPA() <= 0) {
            return 1;
        }
        return 0;
    }

    public void socialNotify() {
        if (happiness <= 10) {
            setChanged();
            notifyObservers();
        }
    }


}
