package com.example.surviveuni.social;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.surviveuni.gameCentre.GameActivity;
import com.example.surviveuni.R;

public class SocialMain extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_main);
    }

    public void StartSocialGame(View view){
        Intent startGame = new Intent(this, SocialActivity.class);
        startActivity(startGame);
    }

    public void ExitSocialGame(View view) {
        Intent exitGame = new Intent(this, GameActivity.class);
        startActivity(exitGame);
    }
}
