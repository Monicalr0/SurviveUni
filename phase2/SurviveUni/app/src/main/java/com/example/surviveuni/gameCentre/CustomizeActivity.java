package com.example.surviveuni.gameCentre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.surviveuni.R;
import com.example.surviveuni.data.User;

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
        setNewGameBtn();
        setLoadGameBtn();
        setSaveGameBtn();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (GameManager.getGameState() == null) {
            findViewById(R.id.CustomizeSaveBtn).setVisibility(View.GONE);
        } else {
            findViewById(R.id.CustomizeSaveBtn).setVisibility(View.VISIBLE);

        }
    }


    public void setNewGameBtn() {
        findViewById(R.id.CustomizeNewGameBtn).setOnClickListener(v -> {
            gameManager.newGame();

            Intent i = new Intent(this, GameActivity.class);
            i.putExtra("User",user);
            startActivity(i);

        });

    }

    public void setLoadGameBtn() {
        findViewById(R.id.CustomizeLoadGameBtn).setOnClickListener(v -> {
            gameManager.loadGame();

            Intent i = new Intent(this, GameActivity.class);
            i.putExtra("User",user);
            startActivity(i);

        });

    }

    public void setSaveGameBtn() {
        findViewById(R.id.CustomizeSaveBtn).setOnClickListener(v -> {

            gameManager.saveGame();
            Toast.makeText(this, "Your game is successfully saved!", Toast.LENGTH_SHORT).show();

        });
    }

}
