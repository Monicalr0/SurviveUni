package com.example.surviveuni.gameCentre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.surviveuni.R;
import com.example.surviveuni.data.GameState;
import com.example.surviveuni.data.User;
import com.example.surviveuni.sleep.SleepAnswerActivity;

public abstract class FeedbackActivity extends AppCompatActivity{
    protected GameState gameState;
    public ScoreManager scoreManager;
    public String feedback;
    public User user;
    private UserManager userManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        gameState = GameManager.getGameState();
        userManager = UserManager.getInstance(this);


        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.feedbackText);
        textView.setText(feedback);

        if (feedback == null) {
            feedback = "Sorry!";
        }
        scoreManager.changeGameState(feedback);
        String statsFeedback = scoreManager.checkFeedback(feedback);
        TextView textView2 = findViewById(R.id.statsText);
        textView2.setText(statsFeedback);
    }

    public void setUser(User user){this.user = user;}

    public void nextRound(View view) {
        Intent NextRound;
        if (gameState.checkGameover() == 1) {
            NextRound = new Intent(this, GameOverActivity.class);
        } else {
            gameState.updateDay();
            NextRound = new Intent(this, GameActivity.class);
        }
        NextRound.putExtra("User",user);
        startActivity(NextRound);
        finish();
    }

    public void setSleepSaveBtn(View view) {
        userManager.getUsers().get(user.getUsername()).updateScore(gameState.getGPA() + gameState.getHappiness() + gameState.getSpirit());
        UserManager.getInstance(this).SaveToFile(); // Save to file so no need to save again when sign out
    }
}
