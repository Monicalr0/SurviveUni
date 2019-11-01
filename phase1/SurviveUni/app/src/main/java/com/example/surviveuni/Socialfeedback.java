package com.example.surviveuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Socialfeedback extends AppCompatActivity {
    private GameState gameState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_feedback);
        Intent intent = getIntent();
        String feedback = intent.getStringExtra(SocialActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.feedbackText);
        textView.setText(feedback);

        String statsFeedback = checkFeedback(feedback);
        TextView textView2 = findViewById(R.id.statsText);
        textView2.setText(statsFeedback);
    }
    @Override
    protected void onResume() {
        super.onResume();
        gameState = GameManager.getGameState();
    }

    private String checkFeedback(String feedback) {
        if (feedback.equals("Correct! Let's be friend!")) {
            gameState.changeGPA(-5);
            gameState.changeSpirit(-5);
            gameState.changeHappiness(10);
            return ("Social: +10\nStudy:-5\nSleep:-5");
        } else {
            gameState.changeGPA(-5);
            gameState.changeSpirit(-5);
            gameState.changeHappiness(-5);
            return ("Study:-5\nSleep:-5");
        }
    }

    public void StartNextRound(View view) {
        Intent NextRound = new Intent(this, GameActivity.class);
        startActivity(NextRound);
    }
}
