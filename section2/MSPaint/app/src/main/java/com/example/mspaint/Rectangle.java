package com.example.mspaint;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Rectangle extends Shape{
    float x2;
    float y2;
    public Rectangle(float x, float y, float x2, float y2) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void drawShape(Canvas canvas, Paint paint) {
        canvas.drawRect(x, y, x2, y2, paint);
    }
}
