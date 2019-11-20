package com.example.surviveuni;

import android.content.Context;
import android.util.Log;

import com.example.surviveuni.data.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

class UserManager {
    static Map<String, User> users = new HashMap<>();
    private Context context;
    private static final String FILENAME = "users.txt";

    public UserManager(Context context) {
        this.context = context;
        loadUsers();
        SaveToFile();
    }

    private void loadUsers() {
        try {
            InputStream inputStream = context.openFileInput(FILENAME);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                users = (HashMap<String, User>) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
            users = new HashMap<>();
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
            users = new HashMap<>();
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
            users = new HashMap<>();
        }
    }


    void SaveToFile() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(FILENAME, context.MODE_PRIVATE));
            outputStream.writeObject(users);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    User authenticate(String username, String password) {
        if (!users.containsKey(username)) throw new InputMismatchException();
        if (!users.get(username).checkPassword(password)) throw new InputMismatchException();
        return users.get(username);
    }


}
