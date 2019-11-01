package com.example.surviveuni;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class GameManager {
    private static GameState gameState;
    private User user;
    private Context context;
    private static final String SUFFIX = "-sav1.dat";

    public GameManager(User user, Context context) {
        this.user = user;
        this.context = context;
    }

    void newGame(){}

    void newStudyGame(){
        gameState.setGPA(0);
    }

    void newSleepGame(){
        gameState.setSpirit(0);
    }

    void newSocialGame(){
        gameState.setHapiness(0);
    }

    void loadGame(){

        String filename = user.getUsername() + SUFFIX;
        try {
            InputStream inputStream = context.openFileInput(filename);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                gameState = (GameState) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("GameState", "FileNotFoundException");
            gameState = new GameState();

        } catch (IOException e) {
            Log.e("GameState", "IOException");
            gameState = new GameState();

        } catch (ClassNotFoundException e) {
            Log.e("GameState", "ClassNotFoundException");
            gameState = new GameState();
        }
    }

    void saveGame(){
        String filename = user.getUsername() + SUFFIX;
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(filename, context.MODE_PRIVATE));
            outputStream.writeObject(gameState);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    static GameState getGameState(){
        return gameState;
    }






















}
