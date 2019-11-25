package com.example.surviveuni.sleep;

import android.content.Intent;
import android.os.Bundle;

import com.example.surviveuni.gameCentre.FeedbackActivity;


public class SleepFeedbackActivity extends FeedbackActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        feedback = intent.getStringExtra(SleepAnswerActivity.EXTRA_MESSAGE);
        scoreManager = new SleepScoreManager();
        super.onCreate(savedInstanceState);
    }
}
