package com.example.surviveuni.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.example.surviveuni.data.User;

public class SleepGameActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.surviveuni.sleep.SleepGameActivity.MESSAGE";
    private int SheepNum = (int) (Math.random() * 10) + 5;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new SheepView(this, SheepNum));
        final Handler handler = new Handler();
        Intent i = getIntent();
        user = (User)i.getSerializableExtra("User");
        if(user == null) {
            System.out.println("2222222222");
        }
        handler.postDelayed(() -> {
            Intent intent = new Intent(SleepGameActivity.this, SleepAnswerActivity.class);
            intent.putExtra(EXTRA_MESSAGE, SheepNum);
            intent.putExtra("User",user);
            if(user == null) {
                System.out.println("3333333333");
            }
            SleepGameActivity.this.startActivity(intent);
            SleepGameActivity.this.finish();
        }, 3500);
    }
}
