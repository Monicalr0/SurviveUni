package com.example.surviveuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class SleepGameActivity extends AppCompatActivity {
  public static final String EXTRA_MESSAGE = "com.example.surviveuni.SleepGameActivity.MESSAGE";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(new SheepView(this));
    final Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      public void run() {
        Intent intent = new Intent(SleepGameActivity.this, SleepAnswerActivity.class);
//        intent.putExtra(EXTRA_MESSAGE, SheepManager.SheepNum);
        SleepGameActivity.this.startActivity(intent);
        SleepGameActivity.this.finish();
      }
    }, 5000);
  }
}
