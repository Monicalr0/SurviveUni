package com.example.surviveuni;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

public class StudyGame extends AppCompatActivity {
    private GameState gamestate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_game);


        setClearBtn();
        setPauseBtn();
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

    private void setClearBtn(){
        findViewById(R.id.StudyGameClearBtn).setOnClickListener(v -> {
            gamestate.studyWork();
        });
    }
}
