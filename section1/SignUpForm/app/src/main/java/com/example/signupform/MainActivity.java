package com.example.signupform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        AppCompatTextView userNameLabel = new AppCompatTextView(this);
        userNameLabel.setText("Username");
        AppCompatEditText userNameField = new AppCompatEditText(this);

        AppCompatTextView passwordLabel = new AppCompatTextView(this);
        userNameLabel.setText("Password");
        AppCompatEditText passwordField = new AppCompatEditText(this);
        setContentView(R.layout.activity_main);
    }
}