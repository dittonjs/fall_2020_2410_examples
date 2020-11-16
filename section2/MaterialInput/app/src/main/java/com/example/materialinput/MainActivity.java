package com.example.materialinput;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        MaterialInput input = new MaterialInput(this, "First Name");
        AppCompatButton button = new AppCompatButton(this);
        button.setText("Click me");
        button.setOnClickListener((view) -> {
            Log.d("Output", input.getText().toString());
        });

        mainLayout.addView(input);
        mainLayout.addView(button);

        setContentView(mainLayout);


    }
}