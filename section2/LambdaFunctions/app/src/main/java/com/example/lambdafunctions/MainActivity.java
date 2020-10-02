package com.example.lambdafunctions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatButton button = new AppCompatButton(this);
        button.setText("Click Me!");
//        old way
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("I got pressed", "Yay!");
//            }
//        });

        button.setOnClickListener(view -> {
            Log.d("I got pressed", "Yay!");
        });

        ArrayList<Integer> ages = new ArrayList<Integer>() {
            {
                add(1);
                add(3);
                add(24);
                add(26);
                add(55);

            }
        };

        ages.forEach(age -> {
            Log.d("Ages double", (age * 2) + "");
        });

        ages.removeIf(age -> {
            return age > 30;
        });

        ages.forEach(age -> {
            Log.d("Ages", age + "");
        });

        setContentView(button);
    }
}