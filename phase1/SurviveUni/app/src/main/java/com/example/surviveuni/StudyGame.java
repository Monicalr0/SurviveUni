package com.example.surviveuni;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.TextView;


import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

public class StudyGame extends AppCompatActivity {
    private GameState gamestate;
    private int num1;
    private int num2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_game);

        setSubmitBtn();
        setPauseBtn();
        setPresent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gamestate = GameManager.getGameState();
        setPrompt();
    }

    private void setPrompt() {
        TextView prompt = findViewById(R.id.StudyguessPrompt);
        prompt.setText("Your GPA is "+ gamestate.getGPA());
    }

    private void setSubmitBtn(){
        findViewById(R.id.StudySubmitBtn).setOnClickListener(v -> {
            EditText numIn = findViewById(R.id.StudyInput);
            int number = Integer.parseInt(numIn.getText().toString());
            TextView prompt =findViewById(R.id.StudyguessPrompt);
            if(gamestate.studyCheck(number)){
                gamestate.changeHapiness(5);
                gamestate.changeSpirit(5);
                prompt.setText("Bingo! Your GPA is "+ gamestate.getGPA());
            }
            else{
                gamestate.changeHapiness(-5);
                gamestate.changeSpirit(-5);
                prompt.setText("Sorry! Your GPA is "+ gamestate.getGPA());
            }
        });
    }

    private void setPresent(){
        int a = gamestate.getFirstNum();
        int b = gamestate.getSecondNum();
        TextView out =findViewById(R.id.StudyGamePresent);
        out.setText("Plz Type in the result of "+ a +" + " + b);
    }

    private void setPauseBtn() {
        findViewById(R.id.StudyGamePauseBtn).setOnClickListener(v -> {
            Intent i = new Intent(this, StudyMenu.class);
            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
        });
    }




}
