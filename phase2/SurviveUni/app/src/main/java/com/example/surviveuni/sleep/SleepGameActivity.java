package com.example.surviveuni.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.example.surviveuni.data.User;

public class SleepGameActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.surviveuni.sleep.SleepGameActivity.MESSAGE";
    private int SheepNum;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        Intent level = getIntent();
        String levelSelected = level.getStringExtra(SleepMainActivity.EXTRA_MESSAGE);
        user = (User)level.getSerializableExtra("User");
        setSheepNum(levelSelected);

        setContentView(new SheepView(this, SheepNum));


        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(SleepGameActivity.this, SleepAnswerActivity.class);
            intent.putExtra(EXTRA_MESSAGE, SheepNum);
            intent.putExtra("User",user);
            SleepGameActivity.this.startActivity(intent);
            SleepGameActivity.this.finish();
        }, 4000);
    }

    private void setSheepNum(String levelSelected) {
        switch (levelSelected) {
            case "HARD":
                SheepNum = (int) (Math.random() * 5) + 13;
                break;
            case "NORMAL":
                SheepNum = (int) (Math.random() * 5) + 8;
                break;
            case "EASY":
                SheepNum = (int) (Math.random() * 5) + 3;
                break;
        }
    }
}
