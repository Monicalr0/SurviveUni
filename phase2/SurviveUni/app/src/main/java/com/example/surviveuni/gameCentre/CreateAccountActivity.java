package com.example.surviveuni.gameCentre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.surviveuni.R;
import com.example.surviveuni.data.User;

public class CreateAccountActivity extends AppCompatActivity {

    /**
     * The userManager manages users
     */
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        userManager = UserManager.getInstance(this);
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
            EditText nickname = findViewById(R.id.CreateAccountNickName);

            String accInput = accIn.getText().toString();
            String psInput = psIn.getText().toString();
            String nkInput = nickname.getText().toString();

            if (checkReasonable(accInput, psInput)) {
                User user = new User(accInput, psInput);
                user.setNickName(nkInput);
                userManager.getUsers().put(accInput, user);
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
        } else {
            for (String key : userManager.getUsers().keySet())
            {
                if (key.equals(username))
                {
                    Toast.makeText(this, "Username has already been taken", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        return true;
    }
}
