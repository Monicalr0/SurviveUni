package com.example.surviveuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
}
