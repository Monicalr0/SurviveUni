package com.example.surviveuni;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.InputMismatchException;

public class MainActivity extends AppCompatActivity {

    private UserManager userManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userManager = new UserManager(this);
        setLogInBtn();
    }

    private void setLogInBtn() {
    }
}
