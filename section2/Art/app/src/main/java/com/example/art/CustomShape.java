package com.example.art;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class CustomShape {
    protected float x;
    protected float y;

    public abstract void drawShape(Canvas canvas, Paint paint);
}
