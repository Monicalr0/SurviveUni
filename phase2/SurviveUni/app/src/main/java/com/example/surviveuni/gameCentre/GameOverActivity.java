package com.example.surviveuni.gameCentre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.surviveuni.R;
import com.example.surviveuni.data.GameState;
import com.example.surviveuni.data.User;
import com.example.surviveuni.gameCentre.GameActivity;
import com.example.surviveuni.gameCentre.GameManager;

public class GameOverActivity extends AppCompatActivity {
    private GameState gameState;
    String reason;
    String record;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gameState = GameManager.getGameState();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        record = String.valueOf(gameState.getDayOfSurvival());
        reason = "Sorry\n you failed to survive university\nyour record is " + record + " days";
        TextView textView = findViewById(R.id.reasonText);
        textView.setText(reason);
        reset_data();

        //get user here
        Intent i = getIntent();
        user = (User) i.getSerializableExtra("User");
    }

    /**
     * reset static variables to 50
     */
    void reset_data() {
        gameState.setSpirit(50);
        gameState.setGPA(50);
        gameState.setHappiness(50);
        gameState.setDayOfSurvival(0);
    }

    /**
     * Navigate to GameActivity
     */
    public void NewGame(View view) {
        Intent newgame = new Intent(this, GameActivity.class);
        newgame.putExtra("User", user);
        startActivity(newgame);
    }
}
