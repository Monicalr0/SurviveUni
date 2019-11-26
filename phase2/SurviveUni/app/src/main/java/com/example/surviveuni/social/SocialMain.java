package com.example.surviveuni.social;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.surviveuni.data.User;
import com.example.surviveuni.gameCentre.GameActivity;
import com.example.surviveuni.R;

public class SocialMain extends AppCompatActivity {
    private User user;

    /**
     * The list of choices for the spinner that allows the user to choose which
     * Java feature to demonstrate.
     */
    private final String[] levels = {"EASY", "NORMAL", "HARD"};
    private Spinner spinner;
    private String levelChoice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_main);
        setLevelSpinner();

        //get user here
        Intent i = getIntent();
        user = (User)i.getSerializableExtra("User");
        if(user == null){
            System.out.println("111111111");
        }
    }

    public void StartSocialGame(View view) {
        levelChoice = spinner.getSelectedItem().toString();
        Intent startGame = new Intent(this, SocialActivity.class);
        startGame.putExtra("User",user);
        startActivity(startGame);
    }

    public void ExitSocialGame(View view) {
        Intent exitGame = new Intent(this, GameActivity.class);
        exitGame.putExtra("User",user);
        startActivity(exitGame);
    }

    /**
     * Set up the gameLevel spinner
     **/
    public void setLevelSpinner() {
        spinner = findViewById(R.id.gameLevel);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, levels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
