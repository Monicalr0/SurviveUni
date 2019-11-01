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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        record = String.valueOf(gameState.getDayOfSurvival());
        reason = "Sorry\n you failed to survive university\nyour record is\n" + record + "days";
        TextView textView = findViewById(R.id.reasonText);
        textView.setText(reason);
    }

    public void NewGame(View view) {
        Intent newgame = new Intent(this, GameActivity.class);
        startActivity(newgame);
    }
}
