package com.example.surviveuni.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.surviveuni.GameActivity;
import com.example.surviveuni.GameManager;
import com.example.surviveuni.GameOverActivity;
import com.example.surviveuni.data.GameState;
import com.example.surviveuni.R;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;


public class StudyGame extends AppCompatActivity {

    /**
     * TextView for displaying time
     */
    private TextView timeDisplay;
    private GameState gameState;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startingTime = LocalTime.now();
        gameState = GameManager.getGameState();
        setContentView(R.layout.activity_study_game);
        setupTime();


    }


    /**
     * Time counting
     */
    private void setupTime() {
        Timer timer;
        timeDisplay = findViewById(R.id.studyTimeText);
        timer = new Timer();

        task2 = new TimerTask() {
            @Override
            public void run() {
                long time = Duration.between(startingTime, LocalTime.now()).toMillis();

                usedTime = convertTime(time);

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
                        timeDisplay.setText(String.format("Time: %s", usedTime));
                    });
                }
            }
        };
        timer.schedule(task2, 0, 1000);
    }

    int convertTime(long time) {
        Integer sec = (int) ((time % 3600000 % 60000) / 1000);


        if (sec == 6)
            setUpResult(false);

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
        startActivity(i);
    }

    public void setUpResult(boolean isSuccess) {

        result = findViewById(R.id.studyResult);
        runOnUiThread(() -> {
                    gameState.changeHappiness(-5);
                    gameState.changeSpirit(-5);

                    // Stuff that updates the UI
                    if (isSuccess && usedTime < 3) {
                        result.setText("Success! GPA goes up!");
                        gameState.changeGPA(5);
                    } else {
                        task2.cancel();
                        result.setText("Failure... :(");
                        gameState.changeGPA(-5);
                    }
                }
        );
    }
}
