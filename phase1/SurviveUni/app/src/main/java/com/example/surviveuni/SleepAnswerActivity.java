package com.example.surviveuni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class SleepAnswerActivity extends AppCompatActivity {
  public static final String EXTRA_MESSAGE = "com.example.surviveuni.MESSAGE";
  private int sheepNum;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sleep_answer);
//    Intent intent = getIntent();
//    sheepNum = intent.getIntExtra(SleepGameActivity.EXTRA_MESSAGE,0);
    sheepNum=SheepManager.SheepNum;
  }

  public void submitAnswer(View view) {
    Intent intent = new Intent(this, SleepFeedbackActivity.class);
    EditText editText = (EditText) findViewById(R.id.answerText);
    String answer = editText.getText().toString();
    String feedBack = checkAnswer(answer);
    intent.putExtra(EXTRA_MESSAGE, feedBack);
    startActivity(intent);
  }

  private String checkAnswer(String answer) {
    int number = Integer.parseInt(answer);
    if (number == sheepNum) {
      return "Correct!";
      // update(stats);
    } else {
      return "Sorry!";
      // update(stats);
    }
  }
}
