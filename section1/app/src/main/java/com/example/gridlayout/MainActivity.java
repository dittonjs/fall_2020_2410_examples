package com.example.gridlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GridLayout layout = new GridLayout(this);
        layout.setColumnCount(3);

        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                AppCompatButton button = new AppCompatButton(this);
                button.setText("View " + i + " " + j);
                button.setGravity(Gravity.CENTER);
                GradientDrawable background = new GradientDrawable();
                background.setColor(Color.GRAY);
                int[] colors = { Color.RED, Color.BLACK, Color.GREEN, Color.CYAN, Color.YELLOW };
                background.setColors(colors);
                background.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                background.setStroke(2, Color.BLACK);
                button.setBackground(background);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.rowSpec = GridLayout.spec(i, 1, 1);
                params.columnSpec = GridLayout.spec(j, 1, 1);
                params.setMargins(0, 0, 0, 0);
                button.setLayoutParams(params);
                layout.addView(button);
            }

        }

//        AppCompatTextView lastView = new AppCompatTextView(this);
//        lastView.setText("I am the last view");
//        lastView.setGravity(Gravity.CENTER);
//        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
//        params.rowSpec = GridLayout.spec(0, 1, 1);
//        params.columnSpec = GridLayout.spec(0, 3, 1);
//        lastView.setLayoutParams(params);
//        layout.addView(lastView);
        setContentView(layout);
    }
}