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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserManager = new UserManager(this);
        setLogInBtn();
    }

    private setLogInBtn(){
        EditText usernameInput = findViewById(R.id.MainUserNameInput);
        EditText passwordInput = findViewById(R.id.MainPassWordInput);
        Button loginBtn = findViewById(R.id.MainLogInBtn);

        loginBtn.setOnClickListener((v) -> {

            try {
                User user = userManager.authenticate(usernameInput.getText().toString(),
                        passwordInput.getText().toString());

                Intent i = new Intent(this, GameMenu.class);
                i.putExtra("USER", user);
                startActivity(i);

            }
            catch (InputMismatchException e){
                new AlertDialog.Builder(this)
                        .setTitle("Wrong Input")
                        .setMessage("username or password is wrong")
                        .setPositiveButton(android.R.string.yes, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }
}
