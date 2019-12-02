package com.example.surviveuni.social;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.ImageView;

import com.example.surviveuni.data.User;
import com.example.surviveuni.gameCentre.FeedbackActivity;

import com.example.surviveuni.R;


public class SocialFeedbackActivity extends FeedbackActivity {
    private ImageView iv;
    private Social social;
    private SocialFeedbackPresenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_feedback);
        social = new Social();
        presenter = new SocialFeedbackPresenter();
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("User");
        String feedback = intent.getStringExtra(SocialActivity.EXTRA_MESSAGE);

        presenter.checkFeedback(feedback);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.feedbackText);
        textView.setText(feedback);

        String statsFeedback = checkFeedback();
        TextView textView2 = findViewById(R.id.statsText);
        textView2.setText(statsFeedback);
    }

    ;

//    private String checkFeedback(String feedback) {
//        gameState.addObserver(social);
//        gameState.socialNotify();
//        iv = findViewById(R.id.imageView1);
//        if (feedback.equals("Correct! Let's be friend!")) {
//            iv.setImageResource(R.drawable.wow);
//            gameState.changeGPA(-5);
//            gameState.changeSpirit(-5);
//            gameState.changeHappiness(10);
//            return ("Happiness:+10\nGPA:-5\nSpirit:-5");
//        } else if (feedback.equals("Sorry! Run out of playing times:( Maybe next time.")) {
//            iv.setImageResource(R.drawable.sorry);
//            gameState.changeGPA(-5);
//            gameState.changeSpirit(-5);
//            gameState.changeHappiness(-5);
//            return ("Happiness:-5\nGPA:-5\nSpirit:-5");
//        } else {
//            iv.setImageResource(R.drawable.angry);
//            gameState.changeGPA(-5);
//            gameState.changeSpirit(-10);
//            gameState.changeHappiness(-10);
//            return ("Happiness:-10\nGPA:-5\nSpirit:-10");
//        }
//    }

    private String checkFeedback() {
        iv = findViewById(R.id.imageView1);
        if (presenter.getSetImage().equals("wow")) {
            iv.setImageResource(R.drawable.wow);
            return ("Happiness:+10\nGPA:-5\nSpirit:-5");
        } else if (presenter.getSetImage().equals("sorry")) {
            iv.setImageResource(R.drawable.sorry);
            return ("Happiness:-5\nGPA:-5\nSpirit:-5");
        } else {
            iv.setImageResource(R.drawable.angry);
            return ("Happiness:-10\nGPA:-5\nSpirit:-10");
        }
    }
}
