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
            if(checkReasonable(accInput,psInput,prompt))
            {
                for(int i = 0; i < 100; i++) {
                    System.out.println("000000000000000000000000000000");
                }
                UserManager.users.put(accInput,new User(accInput,psInput));
                for(int i = 0; i < 100; i++) {
                    System.out.println("11111111111111111111111111111");
                }
                System.out.println(UserManager.users.get(accInput).getPassword());
                prompt.setText("Your new acccount has been created!");
                Intent back = new Intent(this, MainActivity.class);
                startActivity(back);
            }
        });

    }

    private boolean checkReasonable(String username,String password,TextView prompt){
        if (username.equals("")) {
            prompt.setText("Username Cannot Be Empty!");
            return false;
        } else if (password.equals("")) {
            prompt.setText("Password Cannot Be Empty!");
            return false;
        } else if (password.length() <= 6 || username.length() <= 6) {
            prompt.setText("Length of Password And Username must be greater than 6");
            return false;
        }
        return true;

    }
}
