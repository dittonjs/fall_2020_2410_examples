package com.example.mspaint;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        ShapeSelector shapeSelector = new ShapeSelector(this);
        linearLayout.addView(shapeSelector);

        DrawingView drawingView = new DrawingView(this);
        drawingView.setOnTouchListener((view, motionEvent) -> {
                return true;
        });
        linearLayout.addView(drawingView);
        setContentView(linearLayout);
    }
}