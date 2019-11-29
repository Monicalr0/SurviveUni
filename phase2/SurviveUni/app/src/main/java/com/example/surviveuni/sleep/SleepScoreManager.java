package com.example.surviveuni.sleep;

import android.content.Context;
import android.util.Log;

import com.example.surviveuni.data.GameState;
import com.example.surviveuni.data.User;
import com.example.surviveuni.gameCentre.GameManager;
import com.example.surviveuni.gameCentre.ScoreManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SleepScoreManager extends ScoreManager {
  private static GameState gameState;
  private User user;
  private Context context;
  private static final String SUFFIX = "-sav2.dat";
  private int touchedWolfNum;

  SleepScoreManager(Context context) {
    gameState = GameManager.getGameState();
    this.user = GameManager.getUser();
    this.context = context;
  }

  public int getTouchedWolfNum() {
    return touchedWolfNum;
  }

  void setTouchedWolfNum(int touchedWolfNum) {
    this.touchedWolfNum = touchedWolfNum;
  }
  /** Modify the static variable according to the game result */
  public void changeGameState(String feedback) {
    if (feedback.equals("Correct!")) {
      gameState.changeSpirit(10);
      gameState.changeGPA(-5);
    } else {
      gameState.changeSpirit(-10);
      gameState.changeGPA(-5);
    }
  }

  /** Return the corresponding message to show the user the changer of data */
  public String checkFeedback(String feedback) {
    if (feedback.equals("Correct!")) {
      return ("Spirit: +10\nGPA:-5");
    } else {
      return ("Spirit: -10\nGPA:-5");
    }
  }

  public void loadGame() {

    String filename = user.getUsername() + SUFFIX;
    try {
      InputStream inputStream = context.openFileInput(filename);
      if (inputStream != null) {
        ObjectInputStream input = new ObjectInputStream(inputStream);
        touchedWolfNum = touchedWolfNum + (int) input.readObject();
        inputStream.close();
      }
    } catch (FileNotFoundException e) {
      Log.e("touchedWolfNum", "FileNotFoundException");
      touchedWolfNum = 0;

    } catch (IOException e) {
      Log.e("touchedWolfNum", "IOException");
      touchedWolfNum = 0;

    } catch (ClassNotFoundException e) {
      Log.e("touchedWolfNum", "ClassNotFoundException");
      touchedWolfNum = 0;
    }
  }

  public void saveGame() {
    String filename = user.getUsername() + SUFFIX;
    try {
      ObjectOutputStream outputStream =
          new ObjectOutputStream(context.openFileOutput(filename, context.MODE_PRIVATE));
      outputStream.writeObject(touchedWolfNum);
      outputStream.close();
    } catch (IOException e) {
      Log.e("Exception", "File write failed: " + e.toString());
    }
  }
}
