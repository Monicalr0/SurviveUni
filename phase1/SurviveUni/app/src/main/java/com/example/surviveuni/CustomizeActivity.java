package com.example.surviveuni;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

public class CustomizeActivity extends AppCompatActivity {
    private User user;
    private GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        Intent i = getIntent();
        user = (User) i.getSerializableExtra("USER");
        gameManager = new GameManager(user, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (GameManager.getGameState() == null) {
            findViewById(R.id.CustomizeResumeBtn).setVisibility(View.GONE);
            findViewById(R.id.CustomizeSaveBtn).setVisibility(View.GONE);
        } else {
            findViewById(R.id.CustomizeResumeBtn).setVisibility(View.VISIBLE);
            findViewById(R.id.CustomizeSaveBtn).setVisibility(View.VISIBLE);

        }
    }


    public void setNewGameBtn(View view){
        findViewById(R.id.CustomizeNewGameBtn).setOnClickListener(v -> {
            gameManager.newGame();

            Intent i = new Intent(this, GameActivity.class);
            startActivity(i);

        });

    }

    public void setLoadGameBtn(View view){
        findViewById(R.id.CustomizeLoadGameBtn).setOnClickListener(v -> {
            gameManager.loadGame();

            Intent i = new Intent(this, GameActivity.class);
            startActivity(i);

        });

    }

    public void setSaveGameBtn(View view){
        findViewById(R.id.CustomizeSaveBtn).setOnClickListener(v -> {

            gameManager.saveGame();
            new AlertDialog.Builder(this)
                    .setTitle("Game Saved")
                    .setMessage("Your game has been successfully saved!")
                    .setPositiveButton(android.R.string.yes, null)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();

        });
    }

    public void setResumeBtn(View view){
        findViewById(R.id.CustomizeResumeBtn).setOnClickListener(v -> {

            Intent i = new Intent(this, Activity.class);
            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);

        });
    }
}
