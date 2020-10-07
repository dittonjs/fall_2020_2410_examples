package com.example.art;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowMetrics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DrawableView extends View {
    Paint paint = new Paint();
    ArrayList<CustomShape> shapes = new ArrayList<>();
    public DrawableView(Context context) {
        super(context);
    }

    public void addShape(CustomShape shape) {
        shapes.add(shape);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        shapes.forEach(shape -> {
            shape.drawShape(canvas, paint);
        });
    }
}
