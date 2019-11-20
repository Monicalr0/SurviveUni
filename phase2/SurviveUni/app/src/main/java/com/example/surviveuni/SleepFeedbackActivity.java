package com.example.surviveuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SleepFeedbackActivity extends AppCompatActivity {
    private GameState gameState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_feedback);
        gameState=GameManager.getGameState();
        Intent intent = getIntent();
        String feedback = intent.getStringExtra(SleepAnswerActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.feedbackText);
        textView.setText(feedback);

        if (feedback == null) {
            feedback = "Sorry!";
        }
        ChangeGameState(feedback);
        String statsFeedback = checkFeedback(feedback);
        TextView textView2 = findViewById(R.id.statsText);
        textView2.setText(statsFeedback);
    }

    private String checkFeedback(String feedback) {
        if (feedback.equals("Correct!")) {
            return ("Spirit: +10\nGPA:-5");
        } else {
            return ("Spirit: -10\nGPA:-5");
        }
    }

    /**
     * Modify the static variable according to the game result
     */
    private void ChangeGameState(String feedback) {
        if (feedback.equals("Correct!")) {
            gameState.changeSpirit(10);
            gameState.changeGPA(-5);
        } else {
            gameState.changeSpirit(-10);
            gameState.changeGPA(-5);
        }
    }

    public void nextRound(View view) {
        Intent NextRound;
        if(gameState.checkGameover() == 1){
            NextRound = new Intent(this, GameOverActivity.class);
        }
        else {
            gameState.updateDay();
            NextRound = new Intent(this, GameActivity.class);
        }
        startActivity(NextRound);
        finish();
    }
}
