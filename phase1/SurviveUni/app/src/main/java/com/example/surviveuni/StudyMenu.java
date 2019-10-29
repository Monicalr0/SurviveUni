package com.example.surviveuni;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

public class StudyMenu extends AppCompatActivity {

    private User user;
    private GameManager Manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_menu);
        Intent i = getIntent();
        user = (User) i.getSerializableExtra("USER");
        Manager = new GameManager(user, this);

        setNewGameBtn();
        setLoadGameBtn();
        setSaveGameBtn();
        setResumeGameBtn();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(GameManager.getGameState() == null){
            findViewById(R.id.studyResumeGameBtn).setVisibility(View.GONE);
            findViewById(R.id.studySaveGameBtn).setVisibility(View.GONE);
        }
        else
        {
            findViewById(R.id.studyResumeGameBtn).setVisibility(View.GONE);
            findViewById(R.id.studySaveGameBtn).setVisibility(View.GONE);
        }
    }

    private void setNewGameBtn(){
        findViewById(R.id.studyNewGameBtn).setOnClickListener(v -> {
            Manager.newStudyGame();

            Intent i = new Intent(this, StudyGame.class);
            startActivity(i);

        });
    }

    private void setLoadGameBtn(){
        findViewById(R.id.studyNewGameBtn).setOnClickListener(v -> {
            Manager.loadGame();

            Intent i = new Intent(this, StudyGame.class);
            startActivity(i);

        });
    }

    private void setSaveGameBtn(){
        findViewById(R.id.studySaveGameBtn).setOnClickListener(v -> {

            Manager.saveGame();
            new AlertDialog.Builder(this)
                    .setTitle("Game Saved")
                    .setMessage("Your game has been successfully saved!")
                    .setPositiveButton(android.R.string.yes, null)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();

        });
    }

    private void setResumeGameBtn(){
        findViewById(R.id.studyResumeGameBtn).setOnClickListener(v -> {
            Intent i = new Intent(this, StudyGame.class);
            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
        });
    }



























}
