package com.example.surviveuni.study;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.surviveuni.R;
import com.example.surviveuni.data.GameState;
import com.example.surviveuni.data.User;
import com.example.surviveuni.gameCentre.GameActivity;
import com.example.surviveuni.gameCentre.GameManager;
import com.example.surviveuni.gameCentre.GameOverActivity;
import com.example.surviveuni.gameCentre.UserManager;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;


public class StudyGameActivity extends AppCompatActivity {
    private User user;
    private ImageButton button;

    private StudyGame studygame;
    private static String TIME_PREFIX = "Time: ";
    private TextView result;
    private TimerTask task2;
    private LocalTime startingTime;
    private int usedTime = 0;
    private AlertDialog.Builder scoreSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GameState gameState;
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_study_game);
        gameState = GameManager.getGameState();

        startingTime = LocalTime.now();

        Intent i = getIntent();
        user = (User) i.getSerializableExtra("User");
        String levelSelected = i.getStringExtra(StudyMenu.EXTRA_MESSAGE);

        findViewById(R.id.StudySaveBtn).setVisibility(View.GONE);
        studygame = new StudyGame(gameState);
        setupTime(levelSelected);
        studygame.passActivity(this);
        studygame.passUser(user);
        studygame.passUserManager(UserManager.getInstance(this));
        scoreSaved = new AlertDialog.Builder(this)
                .setMessage("Your Score Has Been Saved To ScoreBoard")
                .setPositiveButton(android.R.string.yes, null)
                .setIcon(android.R.drawable.ic_dialog_alert);
    }

    void setupTime(String level) {
        Timer timer;
        timer = new Timer();

    task2 =
        new TimerTask() {
          @Override
          public void run() {
            long time = Duration.between(startingTime, LocalTime.now()).toMillis();

            usedTime = studygame.convertTime(time, level);

            if (usedTime == -1) {
              runOnUiThread(
                  () -> {
                    // Stuff that updates the UI

                    double i = Math.random();
                    if (i > 0.5) setUpMessageButton();
                    else setUpPersonButton();
                  });
            } else if (usedTime >= 0) {
              runOnUiThread(
                  () -> {
                    // Stuff that updates the UI
                    setTimeDisplay(usedTime);
                  });
            }
          }
        };
        timer.schedule(task2, 0, 1000);
    }

    void setUpMessageButton() {

        button = new ImageButton(this);
        button = findViewById(R.id.MessageButton);
        button.setImageResource(R.drawable.message1);

        button.setOnClickListener(view -> {
            task2.cancel();
            button.setImageDrawable(null);
            setUpResult(true);
        });
    }

    void setUpPersonButton() {

        button = new ImageButton(this);
        button = findViewById(R.id.theButton);
        button.setImageResource(R.drawable.person1);


        button.setOnClickListener(view -> {
            task2.cancel();
            button.setImageDrawable(null);
            setUpResult(true);

        });
    }

    void setTimeDisplay(int usedTime){
        TextView timeDisplay;
        timeDisplay = findViewById(R.id.studyTimeText);
        timeDisplay.setText(String.format(TIME_PREFIX + "%s", usedTime));
    }

    public void setExitBtn(View view){
        Intent i;
        if(studygame.checkExit()){
            i = new Intent(this, GameOverActivity.class);
        }
        else{
            i = new Intent(this, GameActivity.class);
        }
        i.putExtra("User", user);
        startActivity(i);
    }

    public void setSaveScoreBtn(View view){
        studygame.saveScore();
    }

    void setUpResult(boolean isSuccess){
        result = findViewById(R.id.studyResult);
        runOnUiThread(() -> {
                    // Stuff that updates the UI
                    if (isSuccess && usedTime < 3) {
                        studygame.setUpResult(true);
                        result.setText("Success! GPA goes up!");
                    } else {
                        studygame.setUpResult(false);
                        task2.cancel();
                        result.setText("Failure... :(");
                    }
                    findViewById(R.id.StudySaveBtn).setVisibility(View.VISIBLE);
                }
        );
    }

    void setScoreSaveMessage(){
        scoreSaved.show();

    }
}
