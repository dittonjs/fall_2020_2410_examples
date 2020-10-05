package com.example.multipleactivities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatTextView textView = new AppCompatTextView(this);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        textView.setText("Welcome to the next activity, " + name);
        setContentView(textView);
    }
}

