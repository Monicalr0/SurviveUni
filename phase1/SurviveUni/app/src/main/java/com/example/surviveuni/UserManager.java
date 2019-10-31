package com.example.surviveuni;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

class UserManager {
    Map<String, User> users = new HashMap<>();
    private Context context;
    private static final String FILENAME = "users.dat";

    public UserManager(Context context) {
        this.context = context;
        loadUsers();
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


    User authenticate(String username, String password){

        System.out.println(username+" 111111 ");
        System.out.println(username);
        System.out.println(username);
        System.out.println(username);
        if(!users.containsKey(username)) {
            for(Map.Entry<String,User> kv: users.entrySet()){
                System.out.println(kv.getValue());
            }
            System.out.println("ERROR ERROR ERROR");
            throw new InputMismatchException();

        }

        if(!users.get(username).checkPassowrd(password)) throw new InputMismatchException();

        return users.get(username);
    }


}
