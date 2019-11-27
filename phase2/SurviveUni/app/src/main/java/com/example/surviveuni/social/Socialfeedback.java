package com.example.surviveuni.social;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import com.example.surviveuni.data.User;
import com.example.surviveuni.gameCentre.GameActivity;
import com.example.surviveuni.gameCentre.GameManager;
import com.example.surviveuni.gameCentre.GameOverActivity;
import com.example.surviveuni.data.GameState;
import com.example.surviveuni.R;
import com.example.surviveuni.gameCentre.UserManager;


public class Socialfeedback extends AppCompatActivity {
    private GameState gameState;
    private User user;
    private UserManager userManager;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_feedback);
        gameState = GameManager.getGameState();
        Intent intent = getIntent();
        String feedback = intent.getStringExtra(SocialActivity.EXTRA_MESSAGE);
        user = (User) intent.getSerializableExtra("User");
        userManager = new UserManager(this);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.feedbackText);
        textView.setText(feedback);

        String statsFeedback = checkFeedback(feedback);
        TextView textView2 = findViewById(R.id.statsText);
        textView2.setText(statsFeedback);
    };

    private String checkFeedback(String feedback) {
        iv = findViewById(R.id.imageView1);
        if (feedback.equals("Correct! Let's be friend!")) {
            iv.setImageResource(R.drawable.wow);
            gameState.changeGPA(-5);
            gameState.changeSpirit(-5);
            gameState.changeHappiness(10);
            return ("Happiness:+10\nGPA:-5\nSpirit:-5");
        } else if (feedback.equals("Sorry! Maybe next time.")) {
            iv.setImageResource(R.drawable.sorry);
            gameState.changeGPA(-5);
            gameState.changeSpirit(-5);
            gameState.changeHappiness(-5);
            return ("Happiness:-5\nGPA:-5\nSpirit:-5");
        } else {
            iv.setImageResource(R.drawable.angry);
            gameState.changeGPA(-5);
            gameState.changeSpirit(-10);
            gameState.changeHappiness(-10);
            return ("Happiness:-10\nGPA:-5\nSpirit:-10");
        }
    }

    public void StartNextRound(View view) {
        Intent NextRound;
        if (gameState.checkGameover() == 1) {
            NextRound = new Intent(this, GameOverActivity.class);
        } else {
            NextRound = new Intent(this, GameActivity.class);
            gameState.updateDay();
        }
        NextRound.putExtra("User", user);
        startActivity(NextRound);
        finish();
    }

    public void setSocialSaveBtn(View view) {
        userManager.getUsers().get(user.getUsername()).updateScore(gameState.getGPA() + gameState.getHappiness() + gameState.getSpirit());
    }
}
