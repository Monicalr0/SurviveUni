package com.example.surviveuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SleepMainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sleep_main);
  }

  /** Start the game when the user clicks the start button */
  public void StartSleepGame(View view) {
    Intent startGame = new Intent(this, SleepGameActivity.class);
    startActivity(startGame);
  }

  /** Return to the Game main activity when user clicks the exit button */
  public void ReturnGameMain(View viwe) {
      Intent ReturnGame = new Intent(this, GameActivity.class);
      startActivity(ReturnGame);
  }
}