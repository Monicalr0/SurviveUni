package com.example.surviveuni.study;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import com.example.surviveuni.GameActivity;
import com.example.surviveuni.R;


public class StudyMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_menu);

    }

    public void StartStudyGame(View view) {
        Intent startGame = new Intent(this, StudyGame.class);
        startActivity(startGame);

    }

    public void ExitStudyGame(View view) {
        Intent exitGame = new Intent(this, GameActivity.class);
        startActivity(exitGame);

    }
}

