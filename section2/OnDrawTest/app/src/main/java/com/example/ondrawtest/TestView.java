package com.example.ondrawtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class TestView extends View {
    Paint paint = new Paint();
    public TestView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.RED);
        paint.setStrokeWidth(15);
        canvas.drawCircle(200, 200, 45, paint);
    }
}
