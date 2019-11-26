package com.example.surviveuni.study;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.surviveuni.data.User;
import com.example.surviveuni.gameCentre.GameActivity;
import com.example.surviveuni.R;


public class StudyMenu extends AppCompatActivity {
    private User user;
    private final String[] LEVELS = {"EASY", "NORMAL", "HARD"};
    public static final String EXTRA_MESSAGE = "com.example.surviveuni.study.StudyMenu.MESSAGE";
    private Spinner spinner;
    private String levelChoice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_menu);
        Intent i = getIntent();
        user = (User) i.getSerializableExtra("User");

        spinner = findViewById(R.id.studyLevel);

        setLevelSpinner();


    }

    public void StartStudyGame(View view) {
        levelChoice = spinner.getSelectedItem().toString();

        Intent startGame = new Intent(this, StudyGame.class);
        startGame.putExtra(EXTRA_MESSAGE, levelChoice);
        startGame.putExtra("User", user);
        startActivity(startGame);


    }

    public void ExitStudyGame(View view) {
        Intent exitGame = new Intent(this, GameActivity.class);
        exitGame.putExtra("User", user);
        startActivity(exitGame);

    }

    /*
    Set up the level spinner
     */
    private void setLevelSpinner() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, LEVELS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}

