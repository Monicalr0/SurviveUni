package com.example.surviveuni.social;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.surviveuni.data.User;
import com.example.surviveuni.gameCentre.GameActivity;
import com.example.surviveuni.R;

public class SocialMain extends AppCompatActivity {
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_main);

        //get user here
        Intent i = getIntent();
        user = (User)i.getSerializableExtra("User");
        if(user == null){
            System.out.println("111111111");
        }
    }

    public void StartSocialGame(View view) {
        Intent startGame = new Intent(this, SocialActivity.class);
        startGame.putExtra("User",user);
        startActivity(startGame);
    }

    public void ExitSocialGame(View view) {
        Intent exitGame = new Intent(this, GameActivity.class);
        exitGame.putExtra("User",user);
        startActivity(exitGame);
    }
}
