package com.example.surviveuni.social;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.surviveuni.R;
import com.example.surviveuni.data.User;

public class SocialActivity extends AppCompatActivity {
    private User user;
    public static final String EXTRA_MESSAGE = "com.example.surviveuni.social.SocialActivity.MESSAGE";
    int remainingGuess;
    private String feedBack = "";
    private boolean unexpectedInput = false;
    private Social social;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent level = getIntent();
        String levelSelected = level.getStringExtra(SocialMain.EXTRA_MESSAGE);
        user = (User)level.getSerializableExtra("User");
        social = new Social();
        social.setRemainingGuess(levelSelected);
        remainingGuess = social.getRemainingGuess();
        setContentView(R.layout.activity_social_answer);
        social.passSocialActivity(this);
    }



    public void submitAnswer(View view) {
        EditText editText = findViewById(R.id.answerText);
        String answer = editText.getText().toString();

        feedBack = social.checkAnswer(answer);

        if (remainingGuess != 1) {
            remainingGuess--;
            if (!unexpectedInput){
                Toast.makeText(this, feedBack, Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "unexpected input: out of range or not a number", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void checkGameOver() {
            Intent intent = new Intent(this, Socialfeedback.class);
            intent.putExtra(EXTRA_MESSAGE, feedBack);
            intent.putExtra("User", user);
            startActivity(intent);
            finish();
    }

    void setUnexpectedInput(boolean unexpectedInput){this.unexpectedInput = unexpectedInput;}

    void setFailedMessage(){Toast.makeText(this, "Sorry! Your answer is not even a number.", Toast.LENGTH_SHORT).show();}


}
