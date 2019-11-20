package com.example.surviveuni.gameCentre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.surviveuni.R;
import com.example.surviveuni.data.User;
import com.example.surviveuni.gameCentre.UserManager;

public class CreateAccountActivity extends AppCompatActivity {

    /**
     * The userManager manages users
     */
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        userManager = new UserManager(this);
        ExitCreate();
        setCreateBtn();
    }

    /**
     * Navigate to MainActivity
     */
    public void ExitCreate() {
        findViewById(R.id.CreateAccountExitBtn).setOnClickListener(v -> {
            Intent exitCreate = new Intent(this, MainActivity.class);
            startActivity(exitCreate);
        });
    }

    /**
     * create new account
     */
    public void setCreateBtn() {
        findViewById(R.id.CreateAccountSubmitBtn).setOnClickListener(v -> {
            EditText accIn = findViewById(R.id.CreateAccountAccInput);
            EditText psIn = findViewById(R.id.CreateAccountPwInput);

            String accInput = accIn.getText().toString();
            String psInput = psIn.getText().toString();

            if (checkReasonable(accInput, psInput)) {
                UserManager.users.put(accInput, new User(accInput, psInput));
                userManager.SaveToFile();
                Toast.makeText(this, "Your new acccount has been created!", Toast.LENGTH_SHORT).show();
                Intent back = new Intent(this, MainActivity.class);
                startActivity(back);
            }
        });
    }

    /**
     * Check the validity of user's input
     */
    private boolean checkReasonable(String username, String password) {
        if (username.equals("")) {
            Toast.makeText(this, "Username Cannot Be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.equals("")) {
            Toast.makeText(this, "Password Cannot Be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.length() <= 6 || username.length() <= 6) {
            Toast.makeText(this, "Length of Password And Username must be greater than 6", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;

    }
}
