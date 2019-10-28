package com.example.surviveuni;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

public class StudyMenu extends AppCompatActivity {

    private User user;
    private StudyManager studyManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_menu);
        Intent i = getIntent();
        user = (user) i.getSerializableExtra("USER");
        studyManager = new StudyManager(user, this);

        setNewGameBtn();
        setLoadGameBtn();
        setSaveGameBtn();
        setResumeGameBtn();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(studyManager.getGameState == null){
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
            studyManager.newGame();

            Intent i = new Intent(this, studyGame.class);
            startActivity(i);

        });
    }

    private void setLoadGameBtn(){
        findViewById(R.id.studyNewGameBtn).setOnClickListener(v -> {
            studyManager.loadGame();

            Intent i = new Intent(this, studyGame.class);
            startActivity(i);

        });
    }

    private void setSaveGameBtn(){
        findViewById(R.id.studySaveGameBtn).setOnClickListener(v -> {
            studyManager.saveGame();
            new AlertDialog.Builder(this)
                    .setTitle("Game Successfully Saved")
                    .setMessage("Your Game Has Been Successfully Saved")
                    .setPositiveButton(android.R.string.yes, null)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
        });
    }

    private void setResumeGameBtn(){
        findViewById(R.id.studyResumeGameBtn).setOnClickListener(v -> {
            Intent i = new Intent(this, studyGame.class);
            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
        });
    }



























}
