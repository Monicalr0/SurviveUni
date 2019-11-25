package com.example.surviveuni.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.surviveuni.data.User;
import com.example.surviveuni.gameCentre.GameActivity;
import com.example.surviveuni.R;

public class SleepMainActivity extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_main);

        Intent i = getIntent();
        user = (User)i.getSerializableExtra("User");
    }

    /**
     * Start the game when the user clicks the start button
     */
    public void StartSleepGame(View view) {
        Intent startGame = new Intent(this, SleepGameActivity.class);
        startGame.putExtra("User",user);
        startActivity(startGame);
    }

    /**
     * Return to the Game main activity when user clicks the exit button
     */
    public void ReturnGameMain(View view) {
        Intent ReturnGame = new Intent(this, GameActivity.class);
        ReturnGame.putExtra("User",user);
        startActivity(ReturnGame);
    }
}