package com.example.surviveuni;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {
    private GameState gameState;
    String reason;
    String record;
    private GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gameState = GameManager.getGameState();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        record = String.valueOf(gameState.getDayOfSurvival());
        reason = "Sorry\n you failed to survive university\nyour record is " + record + " days";
        TextView textView = findViewById(R.id.reasonText);
        textView.setText(reason);
        reset_data();
    }

    /**
     * reset static variables to 50
     */
    void reset_data() {
        gameState.setSpirit(50);
        gameState.setGPA(50);
        gameState.setHappiness(50);
        gameState.setDayOfSurvival(0);
    }

    /**
     * Navigate to GameActivity
     */
    public void NewGame(View view) {
        Intent newgame = new Intent(this, GameActivity.class);
        startActivity(newgame);
    }
}
