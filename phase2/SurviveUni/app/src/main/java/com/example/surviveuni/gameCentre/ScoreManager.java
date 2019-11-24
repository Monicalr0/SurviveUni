package com.example.surviveuni.gameCentre;

import com.example.surviveuni.data.GameState;

public abstract class ScoreManager {
    public GameState gameState = GameManager.getGameState();;

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
       return null;
    }
}
