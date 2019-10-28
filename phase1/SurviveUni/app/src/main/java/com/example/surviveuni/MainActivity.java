package com.example.surviveuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void LogIn(View view) {
        Intent logIn = new Intent(this, LoginActivity.class);
        startActivity(logIn);
    }

    public void createAccount(View view) {
        Intent createAcc = new Intent(this, CreateAccountActivity.class);
        startActivity(createAcc);
    }
}
