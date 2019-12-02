package com.example.surviveuni.gameCentre;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.surviveuni.data.GameState;
import com.example.surviveuni.data.User;


public abstract class FeedbackActivity extends AppCompatActivity {
    private GameState gameState;
    private User user;
    private UserManager userManager;
    private AlertDialog.Builder scoreSaved;
    public static boolean changed = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManager = UserManager.getInstance(this);
        gameState = GameManager.getGameState();
        scoreSaved = new AlertDialog.Builder(this)
                .setMessage("Your Score Has Been Saved To ScoreBoard")
                .setPositiveButton(android.R.string.yes, null)
                .setIcon(android.R.drawable.ic_dialog_alert);
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
