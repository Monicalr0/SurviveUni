package com.example.surviveuni;

import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SocialActivity extends AppCompatActivity{
    int expect;
    public static final String EXTRA_MESSAGE = "com.example.surviveuni.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_answer);
    }

    public void submitAnswer(View view) {
        Intent intent = new Intent(this, Socialfeedback.class);
        EditText editText = (EditText) findViewById(R.id.answerText);
        String answer = editText.getText().toString();
        String feedBack = checkAnswer(answer);
        intent.putExtra(EXTRA_MESSAGE, feedBack);
        startActivity(intent);
        finish();
    }
    public int generate_expect(){
        Random r = new Random();
        expect = r.nextInt(5)+1; // generate a random number ranging from 1 to 5
        return expect;
    }

    private String checkAnswer(String answer) {
        try{
            int number = Integer.parseInt(answer);
            int correctAnswer = generate_expect(); // should be imported from previous activity
            if (number == correctAnswer) {
                return "Correct! Let's be friend!";
                // update(stats);
            }

            else if(number <=5 && number >=1 ) {
                return "Sorry! Maybe next time.";
                // update(stats);
            }
            else{
                return "You are not here to be friend with me!";
            }
        }catch (NumberFormatException e) {
            Toast.makeText(this, "Sorry! Your answer is not even a number.", Toast.LENGTH_SHORT).show();
            return "Sorry! Maybe next time.";
        }

    }
    public void update(){}

}
