package com.example.mspaint;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle extends Shape {
    float r;
    public Circle(float x, float y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
    @Override
    public void drawShape(Canvas canvas, Paint paint) {
        canvas.drawCircle(x, y, r, paint);
    }
}
