package com.example.surviveuni.sleep;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.surviveuni.R;
import com.example.surviveuni.data.User;
import com.example.surviveuni.gameCentre.FeedbackActivity;
import com.example.surviveuni.gameCentre.ScoreManager;


public class SleepFeedbackActivity extends FeedbackActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String feedback = intent.getStringExtra(SleepAnswerActivity.EXTRA_MESSAGE);

        /**
         * the user playing the game
         */
        user = (User) intent.getSerializableExtra("User");
        ScoreManager scoreManager = new SleepScoreManager(this);
        int touchedWolfNum = intent.getIntExtra("TouchedWolfNum", 0);
        ((SleepScoreManager) scoreManager).setTouchedWolfNum(touchedWolfNum);
        scoreManager.loadGame();
        scoreManager.saveGame();
        String message = "You have already discovered "
                + ((SleepScoreManager) scoreManager).getTouchedWolfNum()
                + " wolves!";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        TextView textView = findViewById(R.id.feedbackText);
        TextView textView3 = findViewById(R.id.secondFeeedback);
        textView.setText(feedback);
        textView3.setText(message);

        if (feedback == null) {
            feedback = "Sorry!";
        }
        scoreManager.changeGameState(feedback);
        String statsFeedback = scoreManager.checkFeedback(feedback);
        TextView textView2 = findViewById(R.id.statsText);
        textView2.setText(statsFeedback);
        super.setUser(user);
    }
}
