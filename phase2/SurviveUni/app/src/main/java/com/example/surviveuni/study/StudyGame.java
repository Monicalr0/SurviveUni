package com.example.surviveuni.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.surviveuni.data.User;
import com.example.surviveuni.gameCentre.GameActivity;
import com.example.surviveuni.gameCentre.GameManager;
import com.example.surviveuni.gameCentre.GameOverActivity;
import com.example.surviveuni.data.GameState;
import com.example.surviveuni.R;
import com.example.surviveuni.gameCentre.UserManager;
import com.example.surviveuni.sleep.SleepMainActivity;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;


public class StudyGame extends AppCompatActivity {

    /**
     * Result feedback message
     */
    private static String SUCCESS_MESSAGE = "Success! GPA goes up!";
    private static String FAILURE_MESSAGE = "Failure... :(";

    /**
     * levels
     */
    private final String[] LEVELS = {"EASY", "NORMAL", "HARD"};

    /**
     * TextView for displaying time
     */
    private TextView timeDisplay;
    private GameState gameState;
    private static String TIME_PREFIX = "Time: ";

    /**
     * Time when the game starts or loads
     */
    private LocalTime startingTime;

    /**
     * Button to click
     */
    private ImageButton button;

    /**
     * timertask
     */
    private TimerTask task2;

    private int usedTime = 0;

    /**
     * Button to click
     */
    private TextView result;

    private User user;

    private UserManager userManager;

    private int timeInterval;

    private StudyGameActivity sga = new StudyGameActivity();

    StudyGame(GameState gameState){
        this.gameState = gameState;
    }

    int convertTime(long time, String level) {
        int sec = (int) ((time % 3600000 % 60000) / 1000);
        int deadline;

        if (level.equals(LEVELS[0]))
            deadline = 6;
        else if (level.equals(LEVELS[1]))
            deadline = 5;
        else
            deadline = 4;

        System.out.println("LEVEL: " + level);
        System.out.println("deadline: " + deadline);

        System.out.println("sec: " + sec);
        if (sec == deadline) {

            sga.setUpResult(false);
        }

        return sec - 3;
    }

    boolean checkExit() {
        if (gameState.checkGameover() == 1) {
            return true;
        } else {
            gameState.updateDay();
            return false;
        }
    }

    void saveScore() {
        userManager.getUsers().get(user.getUsername()).updateScore(gameState.getGPA() + gameState.getHappiness() + gameState.getSpirit());
    }

    public void setUpResult(boolean isSuccess) {
                    gameState.changeHappiness(-5);
                    gameState.changeSpirit(-5);
                    if(isSuccess){gameState.changeGPA(5);}
                    else{gameState.changeGPA(-5);}

    }

    void passActivity(StudyGameActivity sga){this.sga = sga;}

}