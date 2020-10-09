package com.example.mspaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DrawingView extends View {
    private ArrayList<Shape> shapes = new ArrayList<>();
    private Shape currentShape;
    private Paint paint = new Paint();

    public DrawingView(Context context) {
        super(context);
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
        currentShape = shape;
        invalidate();
    }

    public void resizeCurrentShape(float x, float y) {
        currentShape.resizeShape(x, y);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        shapes.forEach(shape -> {
            shape.drawShape(canvas, paint, getResources());
        });
    }
}
