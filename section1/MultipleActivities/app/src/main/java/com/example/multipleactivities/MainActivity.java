package com.example.multipleactivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        AppCompatEditText nameField = new AppCompatEditText(this);
        mainLayout.addView(nameField);
        AppCompatButton button = new AppCompatButton(this);
        button.setText("Go to next page");
        button.setOnClickListener(view -> {
            // todo go to the next page
            Intent intent = new Intent(this, SecondActivity.class);
            String name = nameField.getText().toString();
            intent.putExtra("name", name);
            startActivity(intent);
        });
        mainLayout.addView(button);
        setContentView(mainLayout);

    }
}