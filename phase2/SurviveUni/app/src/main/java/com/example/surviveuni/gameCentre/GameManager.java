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
    private static User user;
    private UserManager userManager;
    private Context context;
    private static final String SUFFIX = "-sav1.dat";
    private static final String FILENAME = "users.txt";

    public GameManager(User user, Context context) {
        GameManager.user = user;
        this.context = context;
        this.userManager = UserManager.getInstance(context);
    }

    public static User getUser(){
        return user;
    }

    void newGame() {
        gameState = new GameState();
    }

    void loadGame() {

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

        // load users
        try {
            InputStream inputStream = context.openFileInput(FILENAME);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                userManager.setUsers((HashMap<String, User>) input.readObject());
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
            userManager.setUsers(new HashMap<>());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
            userManager.setUsers(new HashMap<>());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
            userManager.setUsers(new HashMap<>());
        }
    }

    void saveGame() {
        String filename = user.getUsername() + SUFFIX;
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
            outputStream.writeObject(userManager.getUsers());
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static GameState getGameState() {
        return gameState;
    }


}
