package com.example.mspaint;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Circle extends Shape {
    float r;
    public Circle(float x, float y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
    @Override
    public void drawShape(Canvas canvas, Paint paint, Resources resources) {
        paint.setColor(resources.getColor(R.color.colorAccent, null));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(100);
        canvas.drawCircle(x, y, r, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(x, y, r, paint);
        paint.reset();
    }

    @Override
    public void resizeShape(float x, float y) {
        this.r = Math.abs(x - this.x);
    }
}
