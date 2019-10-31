package com.example.surviveuni;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.InputMismatchException;


public class LoginActivity extends AppCompatActivity {

    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userManager = new UserManager(this);

    }

    public void setLoginBtn(View view){
        EditText usernameInput = findViewById(R.id.LogInAccount);
        EditText passwordInput = findViewById(R.id.LogInPw);
            try {
                User user = userManager.authenticate(usernameInput.getText().toString(),
                        passwordInput.getText().toString());

                Intent i = new Intent(this, GameActivity.class);
                i.putExtra("USER", user);
                startActivity(i);

            } catch (InputMismatchException e) {
                new AlertDialog.Builder(this)
                        .setTitle("Wrong Input")
                        .setMessage("username or password is wrong")
                        .setPositiveButton(android.R.string.yes, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
    }
}
