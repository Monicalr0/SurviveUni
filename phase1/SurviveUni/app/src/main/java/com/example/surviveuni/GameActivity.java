package com.example.surviveuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);
    }

    public void StartSleep(View view){
        Intent startGame = new Intent(this, SleepMainActivity.class);
        startActivity(startGame);
    }

    public void StartSocial(View view){
        Intent startGame = new Intent(this, SocialActivity.class);
        startActivity(startGame);
    }

    public void StartStudy(View view){
        Intent startGame = new Intent(this, StudyMenu.class);
        startActivity(startGame);
    }

}
