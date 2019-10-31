package com.example.surviveuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class CreateAccountActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ExitCreate();

        setCreateBtn();
    }
    public void ExitCreate() {
        findViewById(R.id.CreateAccountExitBtn).setOnClickListener(v ->{
            Intent exitCreate = new Intent(this, MainActivity.class);
            startActivity(exitCreate);
        });
    }

    public void setCreateBtn(){
        findViewById(R.id.CreateAccountSubmitBtn).setOnClickListener(v -> {
            EditText accIn = findViewById(R.id.CreateAccountAccInput);
            EditText psIn = findViewById(R.id.CreateAccountPwInput);


            String accInput = accIn.getText().toString();
            String psInput = psIn.getText().toString();

            TextView prompt = findViewById(R.id.CreateAccountPrompt);
            if(User.checkReasonable(accInput,psInput))
            {
                UserManager.users.put(accInput,new User(accInput,psInput));
                prompt.setText("Your new acccount has been created!");
                Intent back = new Intent(this, MainActivity.class);
                startActivity(back);
            }
            else
            {
                prompt.setText("Try Again!");
            }
        });

    }
}
