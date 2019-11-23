package com.example.surviveuni.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.surviveuni.data.User;
import com.example.surviveuni.gameCentre.GameActivity;
import com.example.surviveuni.gameCentre.GameManager;
import com.example.surviveuni.gameCentre.GameOverActivity;
import com.example.surviveuni.data.GameState;
import com.example.surviveuni.R;

public class SleepFeedbackActivity extends AppCompatActivity {
    private GameState gameState;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_feedback);
        gameState= GameManager.getGameState();
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

        // to get user
        Intent i = getIntent();
        user = (User)i.getSerializableExtra("user");
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


    public void setSleepSaveBtn(View view) {
        user.updateScore(gameState.getGPA()+gameState.getSpirit()+gameState.getHappiness());
    }
}
