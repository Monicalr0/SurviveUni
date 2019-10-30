package com.example.surviveuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CreateAccountActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }
    public void ExitCreate(View view) {
        Intent exitCreate = new Intent(this, MainActivity.class);
        startActivity(exitCreate);
    }

    public void setCreateBtn(){
        EditText accIn = findViewById(R.id.CreateUserInput);
        EditText psIn = findViewById(R.id.CreatePwInput);


        String accInput = accIn.getText().toString();
        String psInput = psIn.getText().toString();

        TextView prompt = findViewById(R.id.CreatePrompt);
        if(User.checkReasonable(accInput,psInput))
        {
            UserManager.users.put(accInput,new User(accInput,psInput));
            prompt.setText("Your new acccount has been created!");
        }
        else
        {
            prompt.setText("Try Again!");
        }
    }
}
