package com.example.surviveuni.gameCentre;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.surviveuni.R;
import com.example.surviveuni.gameCentre.UserManager;
import com.example.surviveuni.data.User;

import java.util.InputMismatchException;


public class LoginActivity extends AppCompatActivity {

    /**
     * the UserManager manages user
     */
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userManager = new UserManager(this);


    }

    /**
     * user sign in
     * check the existence of user
     */
    public void setLoginBtn(View view) {
        EditText usernameInput = findViewById(R.id.LogInAccount);
        EditText passwordInput = findViewById(R.id.LogInPw);
        try {
            User user = userManager.authenticate(usernameInput.getText().toString(),
                    passwordInput.getText().toString());

            Intent i = new Intent(this, CustomizeActivity.class);
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

    /**
     * Navigate to MainActivity
     */
    public void setExitBtn(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


}
