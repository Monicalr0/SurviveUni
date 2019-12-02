package com.example.surviveuni.gameCentre;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.surviveuni.data.GameState;
import com.example.surviveuni.data.User;


public abstract class FeedbackActivity extends AppCompatActivity {
    public GameState gameState;
    public ScoreManager scoreManager;
    public String feedback;
    public User user;
    public UserManager userManager;
    public AlertDialog.Builder scoreSaved;
    public String message;
    public static boolean changed = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameState = GameManager.getGameState();
        userManager = UserManager.getInstance(this);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void nextRound(View view) {
        Intent NextRound;
        if (gameState.checkGameover() == 1) {
            NextRound = new Intent(this, GameOverActivity.class);
        } else {
            gameState.updateDay();
            NextRound = new Intent(this, GameActivity.class);
        }
        NextRound.putExtra("User", user);
        startActivity(NextRound);
        finish();
    }

    public void setSaveBtn(View view) {
        userManager.getUsers().get(user.getUsername()).updateScore(gameState.getGPA() + gameState.getHappiness() + gameState.getSpirit());
        UserManager.getInstance(this).SaveToFile(); // Save to file so no need to save again when sign out
        scoreSaved.show();
        changed = true;
    }
}
