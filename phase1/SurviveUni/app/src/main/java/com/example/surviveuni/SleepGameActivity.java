package com.example.surviveuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class SleepGameActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.surviveuni.SleepGameActivity.MESSAGE";
    private int SheepNum = (int) (Math.random() * 10) + 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new SheepView(this, SheepNum));
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(SleepGameActivity.this, SleepAnswerActivity.class);
            intent.putExtra(EXTRA_MESSAGE, SheepNum);
            SleepGameActivity.this.startActivity(intent);
            SleepGameActivity.this.finish();
        }, 3000);
    }
}
