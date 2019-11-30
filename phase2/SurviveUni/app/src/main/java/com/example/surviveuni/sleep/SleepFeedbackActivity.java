package com.example.surviveuni.sleep;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.surviveuni.data.User;
import com.example.surviveuni.gameCentre.FeedbackActivity;
import com.example.surviveuni.gameCentre.UserManager;

public class SleepFeedbackActivity extends FeedbackActivity {

  /** the user playing the game */
  private User user;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    Intent intent = getIntent();
    feedback = intent.getStringExtra(SleepAnswerActivity.EXTRA_MESSAGE);
    user = (User) intent.getSerializableExtra("User");
    scoreManager = new SleepScoreManager(this);
    int touchedWolfNum = intent.getIntExtra("TouchedWolfNum", 0);
    ((SleepScoreManager) scoreManager).setTouchedWolfNum(touchedWolfNum);
    scoreManager.loadGame();
    scoreManager.saveGame();
    message =
        "You have already discovered "
            + ((SleepScoreManager) scoreManager).getTouchedWolfNum()
            + " wolves!";

    super.onCreate(savedInstanceState);
    super.setUser(user);
  }
}
