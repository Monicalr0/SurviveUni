package com.example.surviveuni;

import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class SocialActivity extends AppCompatActivity{
    int expect;
    public static final String EXTRA_MESSAGE = "com.example.surviveuni.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_answer);
    }

    public void submitAnswer(View view) {
        Intent intent = new Intent(this, SleepFeedbackActivity.class);
        EditText editText = (EditText) findViewById(R.id.answerText);
        String answer = editText.getText().toString();
        String feedBack = checkAnswer(answer);
        intent.putExtra(EXTRA_MESSAGE, feedBack);
        startActivity(intent);
    }
    public int generate_expect(){
        Random r = new Random();
        expect = r.nextInt(5)+1; // generate a random number ranging from 1 to 5
        return expect;
    }

    private String checkAnswer(String answer) {
        int number = Integer.parseInt(answer);
        int correctAnswer = generate_expect(); // should be imported from previous activity
        if (number == correctAnswer) {
            return "Correct!";
            // update(stats);
        } else {
            return "Sorry!";
            // update(stats);
        }
    }
    public void update(){}
    public void compare(){}
    public void helper_compare(){}
    public void draw(){}

}
