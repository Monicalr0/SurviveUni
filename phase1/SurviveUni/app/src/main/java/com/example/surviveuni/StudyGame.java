package com.example.surviveuni;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

public class StudyGame extends AppCompatActivity {
    private GameState gamestate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_game);

    }


    public void setNewTurnBtn(View view){
        setPrompt();
        int a = gamestate.getFirstNum();
        int b = gamestate.getSecondNum();
        TextView out =findViewById(R.id.StudyGameShow);
        out.setText("Plz Type in the result of "+ a +" + " + b);
    }


    private void setPrompt() {
        TextView prompt = findViewById(R.id.StudyGuessPrompt);
        prompt.setText("Your GPA is "+ gamestate.getGPA());
    }

    public void setSubmitBtn(View view){
            EditText numIn = findViewById(R.id.StudyGameInput);
            int number = Integer.parseInt(numIn.getText().toString());
            TextView prompt =findViewById(R.id.StudyGuessPrompt);
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
    }

    public void setExitBtn(View view) {
            Intent i = new Intent(this, StudyMenu.class);
            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
    }




}
