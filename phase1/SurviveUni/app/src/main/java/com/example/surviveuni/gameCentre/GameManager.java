package com.example.surviveuni.gameCentre;

import android.content.Context;
import android.util.Log;

import com.example.surviveuni.data.GameState;
import com.example.surviveuni.data.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class GameManager {
    private static GameState gameState;
    private User user;
    private Context context;
    private static final String SUFFIX1 = "-sav1.dat";
    private static final String FILENAME = "users.txt";

    public GameManager(User user, Context context) {
        this.user = user;
        this.context = context;
    }

    void newGame() {gameState = new GameState();}

    void loadGame(){

        String filename = user.getUsername() + SUFFIX1;
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

        // load users
        try {
            InputStream inputStream = context.openFileInput(FILENAME);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                UserManager.users = (HashMap<String, User>) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
            UserManager.users = new HashMap<>();
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
            UserManager.users = new HashMap<>();
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
            UserManager.users = new HashMap<>();
        }



    }

    void saveGame(){
        // save gamestate
        String filename = user.getUsername() + SUFFIX1;
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(filename, context.MODE_PRIVATE));
            outputStream.writeObject(gameState);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        // save user user so the score is saved
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(FILENAME, context.MODE_PRIVATE));
            outputStream.writeObject(UserManager.users);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static GameState getGameState(){
        return gameState;
    }
}
