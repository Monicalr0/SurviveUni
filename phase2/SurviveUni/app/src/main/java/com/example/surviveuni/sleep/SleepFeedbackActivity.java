package com.example.surviveuni.sleep;

import android.content.Intent;
import android.os.Bundle;

import com.example.surviveuni.data.User;
import com.example.surviveuni.gameCentre.FeedbackActivity;


public class SleepFeedbackActivity extends FeedbackActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        feedback = intent.getStringExtra(SleepAnswerActivity.EXTRA_MESSAGE);
        /**
         * the user playing the game
         */
        User user = (User) intent.getSerializableExtra("User");
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
        super.setUser(user);
    }
}
