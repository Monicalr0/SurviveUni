package com.example.surviveuni.sleep;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.surviveuni.R;
import com.example.surviveuni.data.User;
import com.example.surviveuni.gameCentre.FeedbackActivity;
import com.example.surviveuni.gameCentre.UserManager;


public class SleepFeedbackActivity extends FeedbackActivity {
    /**
     * the user playing the game
     */
    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        feedback = intent.getStringExtra(SleepAnswerActivity.EXTRA_MESSAGE);

        user = (User) intent.getSerializableExtra("User");
        scoreManager = new SleepScoreManager(this);
        int touchedWolfNum = intent.getIntExtra("TouchedWolfNum", 0);
        ((SleepScoreManager) scoreManager).setTouchedWolfNum(touchedWolfNum);
        scoreManager.loadGame();
        scoreManager.saveGame();
        message =
                "You have already discovered "
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

        scoreSaved = new AlertDialog.Builder(this)
                .setMessage("Your Score Has Been Saved To ScoreBoard")
                .setPositiveButton(android.R.string.yes, null)
                .setIcon(android.R.drawable.ic_dialog_alert);
        super.setUser(user);
    }
}
