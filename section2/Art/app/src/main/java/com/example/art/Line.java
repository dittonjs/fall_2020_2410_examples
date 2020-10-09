package com.example.art;

import android.content.res.Resources;
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
    public void drawShape(Canvas canvas, Paint paint, Resources resources) {
        canvas.drawLine(x, y, x2, y2, paint);
    }

    @Override
    public void resizeShape(float x, float y) {
        this.x2 = x;
        this.y2 = y;
    }
}
