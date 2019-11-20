package com.example.surviveuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.surviveuni.data.GameState;
import com.example.surviveuni.sleep.SleepMainActivity;
import com.example.surviveuni.social.SocialMain;
import com.example.surviveuni.study.StudyMenu;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);


        GameState gs = GameManager.getGameState();
        Toast.makeText(this, "Your Three Values, GPA, Spirit and Happiness are "
                + gs.getGPA() + ", " + gs.getSpirit() + ", " + gs.getHappiness(), Toast.LENGTH_SHORT).show();

        TextView day = findViewById(R.id.GameMainDayShow);
        day.setText(" Your record of survive in Uni is " + gs.getDayOfSurvival() + " days ");
    }

    /**
     * Navigate to SleepMain Activity
     */
    public void StartSleep(View view) {
        Intent startGame = new Intent(this, SleepMainActivity.class);
        startActivity(startGame);
    }

    /**
     * Navigate to SocialMain Activity
     */
    public void StartSocial(View view) {
        Intent startGame = new Intent(this, SocialMain.class);
        startActivity(startGame);
    }

    /**
     * Navigate to StudyMenu Activity
     */
    public void StartStudy(View view) {
        Intent startGame = new Intent(this, StudyMenu.class);
        startActivity(startGame);
    }

    /**
     * Navigate to Customize Activity
     */
    public void goBack(View view) {
        Intent i = new Intent(this, CustomizeActivity.class);
        i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
    }

}
