package com.example.art;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Line extends CustomShape {
    private float x2;
    private float y2;
    public Line(float x, float y, float x2, float y2) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void drawShape(Canvas canvas, Paint paint) {
        canvas.drawLine(x, y, x2, y2, paint);
    }
}
