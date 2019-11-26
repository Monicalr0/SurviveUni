package com.example.surviveuni.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.surviveuni.data.User;
import com.example.surviveuni.gameCentre.GameActivity;
import com.example.surviveuni.gameCentre.GameManager;
import com.example.surviveuni.gameCentre.GameOverActivity;
import com.example.surviveuni.data.GameState;
import com.example.surviveuni.R;
import com.example.surviveuni.gameCentre.UserManager;
import com.example.surviveuni.sleep.SleepMainActivity;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;


public class StudyGame extends AppCompatActivity {

    /**
     * Result feedback message
     */
    private static String SUCCESS_MESSAGE = "Success! GPA goes up!";
    private static String FAILURE_MESSAGE = "Failure... :(";

    /**
     * levels
     */
    private final String[] LEVELS = {"EASY", "NORMAL", "HARD"};

    /**
     * TextView for displaying time
     */
    private TextView timeDisplay;
    private GameState gameState;
    private static String TIME_PREFIX = "Time: ";

    /**
     * Time when the game starts or loads
     */
    private LocalTime startingTime;

    /**
     * Button to click
     */
    private ImageButton button;

    /**
     * timertask
     */
    private TimerTask task2;

    private int usedTime = 0;

    /**
     * Button to click
     */
    private TextView result;

    private User user;

    private int timeInterval;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startingTime = LocalTime.now();
        gameState = GameManager.getGameState();
        setContentView(R.layout.activity_study_game);


        Intent i = getIntent();
        user = (User) i.getSerializableExtra("User");
        Intent level = getIntent();
        String levelSelected = level.getStringExtra(StudyMenu.EXTRA_MESSAGE);

        findViewById(R.id.StudySaveBtn).setVisibility(View.GONE);
        setupTime(levelSelected);
    }

    /**
     * Time counting
     */
    private void setupTime(String level) {
        Timer timer;
        timeDisplay = findViewById(R.id.studyTimeText);
        timer = new Timer();

        task2 = new TimerTask() {
            @Override
            public void run() {
                long time = Duration.between(startingTime, LocalTime.now()).toMillis();

                usedTime = convertTime(time, level);

                if (usedTime == -1) {
                    runOnUiThread(() -> {
                                // Stuff that updates the UI

                                double i = Math.random();
                                if (i > 0.5) setUpMessageButton();

                                else
                                    setUpPersonButton();

                            }
                    );
                } else if (usedTime >= 0) {
                    runOnUiThread(() -> {


                        // Stuff that updates the UI
                        timeDisplay.setText(String.format(TIME_PREFIX + "%s", usedTime));
                    });
                }
            }
        };
        timer.schedule(task2, 0, 1000);
    }

    int convertTime(long time, String level) {
        int sec = (int) ((time % 3600000 % 60000) / 1000);
        int deadline;

        if (level.equals(LEVELS[0]))
            deadline = 6;
        else if (level.equals(LEVELS[1]))
            deadline = 5;
        else
            deadline = 4;

        System.out.println("LEVEL: " + level);
        System.out.println("deadline: " + deadline);

        System.out.println("sec: " + sec);
        if (sec == deadline) {

            setUpResult(false);
        }

        return sec - 3;
    }


    private void setUpMessageButton() {

        button = new ImageButton(this);
        button = findViewById(R.id.MessageButton);
        button.setImageResource(R.drawable.message1);


        button.setOnClickListener(view -> {
            task2.cancel();
            button.setImageDrawable(null);
            setUpResult(true);

        });
    }

    private void setUpPersonButton() {

        button = new ImageButton(this);
        button = findViewById(R.id.theButton);
        button.setImageResource(R.drawable.person1);


        button.setOnClickListener(view -> {
            task2.cancel();
            button.setImageDrawable(null);
            setUpResult(true);

        });
    }


    public void setExitBtn(View view) {
        Intent i;
        if (gameState.checkGameover() == 1) {
            i = new Intent(this, GameOverActivity.class);
        } else {
            gameState.updateDay();
            i = new Intent(this, GameActivity.class);
        }
        i.putExtra("User", user);
        startActivity(i);
    }

    public void setStudySaveBtn(View view) {
        UserManager.users.get(user.getUsername()).updateScore(gameState.getGPA() + gameState.getHappiness() + gameState.getSpirit());
    }

    public void setUpResult(boolean isSuccess) {

        result = findViewById(R.id.studyResult);
        runOnUiThread(() -> {
                    gameState.changeHappiness(-5);
                    gameState.changeSpirit(-5);

                    // Stuff that updates the UI
                    if (isSuccess && usedTime < 3) {
                        result.setText(SUCCESS_MESSAGE);
                        gameState.changeGPA(5);
                    } else {
                        task2.cancel();
                        result.setText(FAILURE_MESSAGE);
                        gameState.changeGPA(-5);
                    }
                    findViewById(R.id.StudySaveBtn).setVisibility(View.VISIBLE);
                }
        );
    }
}