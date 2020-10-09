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
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ShapeSelector.ShapeType type = shapeSelector.getCurrentType();
                Shape newShape;
                if (type == ShapeSelector.ShapeType.CIRCLE) {
                    newShape = new Circle(motionEvent.getX(), motionEvent.getY(), 1);
                } else if (type == ShapeSelector.ShapeType.RECT) {
                    newShape = new Rectangle(
                            motionEvent.getX(),
                            motionEvent.getY(),
                            motionEvent.getX(),
                            motionEvent.getY()
                    );
                } else if (type == ShapeSelector.ShapeType.PLUS) {
                    newShape = new PlusShape(motionEvent.getX(), motionEvent.getY(), 1);
                } else {
                    newShape = new Line(
                            motionEvent.getX(),
                            motionEvent.getY(),
                            motionEvent.getX(),
                            motionEvent.getY()
                    );
                }
                drawingView.addShape(newShape);

            } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                drawingView.resizeCurrentShape(motionEvent.getX(), motionEvent.getY());
            }
            return true;
        });
        linearLayout.addView(drawingView);
        setContentView(linearLayout);
    }
}