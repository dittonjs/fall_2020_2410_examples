package com.example.art;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DrawableView view = new DrawableView(this);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        ShapeSelector shapeSelector = new ShapeSelector(this);
        mainLayout.addView(shapeSelector);
        setContentView(mainLayout);
    }
}