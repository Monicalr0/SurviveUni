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
    int expect;
    public static final String EXTRA_MESSAGE = "com.example.surviveuni.social.SocialActivity.MESSAGE";
    int remainingGuess;
    private String feedBack = "";
    boolean gameWon = false;
    int correctAnswer = generate_expect();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_social_answer);
//
//        //get user here
//        Intent i = getIntent();
//        user = (User)i.getSerializableExtra("User");
        Intent level = getIntent();
        String levelSelected = level.getStringExtra(SocialMain.EXTRA_MESSAGE);
        user = (User)level.getSerializableExtra("User");
        setRemainingGuess(levelSelected);
        setContentView(R.layout.activity_social_answer);
    }



    public void submitAnswer(View view) {
        EditText editText = findViewById(R.id.answerText);
        String answer = editText.getText().toString();

        feedBack = checkAnswer(answer);

        if(remainingGuess == 1){
            checkGameOver(feedBack, true, false);
        }
        else {
            checkGameOver(feedBack, false, false);
            remainingGuess--;
            Toast.makeText(this,feedBack, Toast.LENGTH_SHORT).show();

        }
    }


    public int generate_expect() {
        Random r = new Random();
        expect = r.nextInt(5) + 1; // generate a random number ranging from 1 to 5
        return expect;
    }

    private void checkGameOver(String feedBack, boolean limitStatus, boolean unExpectInput) {

        if(gameWon || limitStatus || unExpectInput){
            Intent intent = new Intent(this, Socialfeedback.class);
            intent.putExtra(EXTRA_MESSAGE, feedBack);
            intent.putExtra("User", user);
            startActivity(intent);
            finish();
        }
    }

    // should also check if remaining guesses are great than or equal to 1
    private String checkAnswer(String answer) {
        String feedback;
        boolean unexpectInput = false;
        try {
            int number = Integer.parseInt(answer);

            if ( number > 5 || number < 1 ) {
                feedback = "You are not here to be friend with me!";
                unexpectInput = true;
            }
            else if(remainingGuess == 1){
                if (number == correctAnswer) {
                    feedback = "Correct! Let's be friend!";
                } else {
                    feedback =  "Sorry! Run out of playing times:( Maybe next time.";
                }
            }
            else{
                if (number == correctAnswer) {
                    gameWon = true;
                    feedback =  "Correct! Let's be friend!";
                }
                else if(number > correctAnswer){
                    feedback = "It's too high, try another time.";
                }
                else {
                    feedback = "It's too low, try another time.";
                }
            }
        }
        catch (NumberFormatException e) {
            Toast.makeText(this, "Sorry! Your answer is not even a number.", Toast.LENGTH_SHORT).show();
            unexpectInput = true;
            feedback = "You are not here to be friend with me!";
        }
        checkGameOver(feedback, remainingGuess == 1, unexpectInput);
        return feedback;
    }

    private void setRemainingGuess(String level) {
        switch (level) {
            case "HARD":
                remainingGuess = 1;
                break;
            case "NORMAL":
                remainingGuess = 2;
                break;
            case "EASY":
                remainingGuess = 3;
                break;
        }
    }

}
