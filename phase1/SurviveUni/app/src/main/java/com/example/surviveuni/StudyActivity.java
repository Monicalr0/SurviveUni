package com.example.surviveuni;

import android.os.Bundle;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

class StudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new StudyRoomView(context));

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }
}
