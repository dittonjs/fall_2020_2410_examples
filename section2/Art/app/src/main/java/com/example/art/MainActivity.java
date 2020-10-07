package com.example.art;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        view.setBackgroundColor(Color.RED);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        ShapeSelector shapeSelector = new ShapeSelector(this);
        DrawableView drawableView = new DrawableView(this);
        drawableView.setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                ShapeSelector.ShapeType type = shapeSelector.getSelectedType();
                CustomShape shape;
                if (type == ShapeSelector.ShapeType.CIRCLE) {
                    // draw circle
                    shape = new Circle(event.getX(), event.getY(), 50);
                } else if (type == ShapeSelector.ShapeType.RECT) {
                    shape = new Rectangle(event.getX(), event.getY(), event.getX() + 100,event.getY() + 100);
                } else {
                    shape = new Line(event.getX(), event.getY(), event.getX() + 100,event.getY() + 100);
                }
                drawableView.addShape(shape);
            }

            return true;
        });
        mainLayout.addView(shapeSelector);
        mainLayout.addView(drawableView);
        setContentView(mainLayout);
    }
}