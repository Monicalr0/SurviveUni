package com.example.surviveuni;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

class UserManager {
    private Map<String, User> users;
    private Context context;
    private static final String FILENAME = "users.dat";

    public UserManager(Context context) {
        this.context = context;
        loadUsers();
        loadDemoUsers();
    }

    private void loadUsers(){
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


    private void SaveToFile(){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(FILENAME, context.MODE_PRIVATE));
            outputStream.writeObject(users);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private void loadDemoUsers(){
        users.put("garyk", new User("kevinzzz", "1234567"));
        users.put("kevinw", new User("kevinyyy", "123123123"));
    }


}
