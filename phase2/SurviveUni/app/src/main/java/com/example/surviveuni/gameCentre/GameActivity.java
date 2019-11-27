package com.example.surviveuni.gameCentre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.surviveuni.R;
import com.example.surviveuni.data.GameState;
import com.example.surviveuni.data.User;
import com.example.surviveuni.sleep.SleepMainActivity;
import com.example.surviveuni.social.SocialMain;
import com.example.surviveuni.study.StudyMenu;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

public class GameActivity extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);

        Intent i = getIntent();
        user = (User) i.getSerializableExtra("User");

        GameState gs = GameManager.getGameState();
        Toast.makeText(this, "Your Three Values, GPA, Spirit and Happiness are "
                + gs.getGPA() + ", " + gs.getSpirit() + ", " + gs.getHappiness(), Toast.LENGTH_SHORT).show();

        TextView day = findViewById(R.id.textTitle);
        day.setText(" Survive for " + gs.getDayOfSurvival() + " days ");

        TextView gpa = findViewById(R.id.textGPA);
        gpa.setText("GPA:" + gs.getGPA());

        TextView happiness = findViewById(R.id.textHappiness);
        happiness.setText("Happiness:" + gs.getHappiness());

        TextView spirit = findViewById(R.id.textSpirit);
        spirit.setText("Spirit:" + gs.getSpirit());
    }

    /**
     * Navigate to SleepMain Activity
     */
    public void StartSleep(View view) {
        Intent startGame = new Intent(this, SleepMainActivity.class);
        startGame.putExtra("User", user);
        startActivity(startGame);
    }

    /**
     * Navigate to SocialMain Activity
     */
    public void StartSocial(View view) {
        Intent startGame = new Intent(this, SocialMain.class);
        startGame.putExtra("User", user);
        startActivity(startGame);
    }

    /**
     * Navigate to StudyMenu Activity
     */
    public void StartStudy(View view) {
        Intent startGame = new Intent(this, StudyMenu.class);
        startGame.putExtra("User", user);
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

    public void setScoreBrdBtn(View view) {
        Intent i = new Intent(this, ScoreBoardActivity.class);
        i.putExtra("User", user);
        startActivity(i);
    }

}
