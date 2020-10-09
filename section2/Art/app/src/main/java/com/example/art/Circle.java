package com.example.art;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.res.ResourcesCompat;

public class Circle extends CustomShape {
    private float r;
    public Circle(float x, float y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public void drawShape(Canvas canvas, Paint paint, Resources resources) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(100);
        paint.setColor(resources.getColor(R.color.colorAccent, null));
        canvas.drawCircle(x, y, r, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(resources.getColor(R.color.colorPrimary, null));
        canvas.drawCircle(x, y, r, paint);
        paint.reset();
    }

    @Override
    public void resizeShape(float x, float y) {
        this.r = Math.abs(x - this.x);
    }
}
