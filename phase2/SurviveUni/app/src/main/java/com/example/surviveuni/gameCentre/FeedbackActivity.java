package com.example.surviveuni.gameCentre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.surviveuni.R;
import com.example.surviveuni.data.GameState;

public abstract class FeedbackActivity extends AppCompatActivity{
    protected GameState gameState;
    public ScoreManager scoreManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void nextRound(View view) {
        Intent NextRound;
        if (gameState.checkGameover() == 1) {
            NextRound = new Intent(this, GameOverActivity.class);
        } else {
            gameState.updateDay();
            NextRound = new Intent(this, GameActivity.class);
        }
        startActivity(NextRound);
        finish();
    }
}
