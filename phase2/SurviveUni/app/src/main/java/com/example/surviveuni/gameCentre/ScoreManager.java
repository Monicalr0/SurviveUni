package com.example.surviveuni.gameCentre;

import com.example.surviveuni.data.GameState;

public abstract class ScoreManager {
    public GameState gameState = GameManager.getGameState();

    public void changeGameState(String feedback) {
    }

    public String checkFeedback(String feedback) {
        return null;
    }
}
