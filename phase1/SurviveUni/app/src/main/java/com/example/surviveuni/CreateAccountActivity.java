package com.example.surviveuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
    public void SubmitCreate(){}
}
