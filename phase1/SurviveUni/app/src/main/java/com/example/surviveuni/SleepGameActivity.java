package com.example.surviveuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class SleepGameActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(new SheepView(this));
    final Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      public void run() {
        // TODO: Your application init goes here.
        Intent answer = new Intent(SleepGameActivity.this, SleepAnswerActivity.class);
        SleepGameActivity.this.startActivity(answer);
        SleepGameActivity.this.finish();
      }
    }, 5000);
  }
}
