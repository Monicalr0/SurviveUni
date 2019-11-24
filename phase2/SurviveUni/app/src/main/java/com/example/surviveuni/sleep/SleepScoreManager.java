package com.example.surviveuni.sleep;

import com.example.surviveuni.data.GameState;
import com.example.surviveuni.gameCentre.ScoreManager;

public class SleepScoreManager extends ScoreManager {
    public void changeGameState(String feedback) {
        if (feedback.equals("Correct!")) {
            gameState.changeSpirit(10);
            gameState.changeGPA(-5);
        } else {
            gameState.changeSpirit(-10);
            gameState.changeGPA(-5);
        }
    }
    public String checkFeedback(String feedback) {
        if (feedback.equals("Correct!")) {
            return ("Spirit: +10\nGPA:-5");
        } else {
            return ("Spirit: -10\nGPA:-5");
        }
    }
}
