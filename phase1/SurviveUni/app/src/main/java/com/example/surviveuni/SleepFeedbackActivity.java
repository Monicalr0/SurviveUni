package com.example.surviveuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SleepFeedbackActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sleep_feedback);
    Intent intent = getIntent();
    String feedback = intent.getStringExtra(SleepAnswerActivity.EXTRA_MESSAGE);

    // Capture the layout's TextView and set the string as its text
    TextView textView = findViewById(R.id.feedbackText);
    textView.setText(feedback);

    String statsFeedback = checkFeedback(feedback);
    TextView textView2 = findViewById(R.id.statsText);
    textView2.setText(statsFeedback);
  }

  private String checkFeedback(String feedback) {
    if (feedback.equals("Correct!")) {
      return ("Health: +10\nStudy:-5");
    } else {
      return ("Health: -10\nStudy:-5");
    }
  }

  public void nextRound(View view) {
    Intent nxRound = new Intent(this, GameActivity.class);
    startActivity(nxRound);
  }
}
