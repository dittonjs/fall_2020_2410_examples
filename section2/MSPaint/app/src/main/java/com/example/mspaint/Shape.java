package com.example.mspaint;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Shape {
    protected float x;
    protected float y;

    public abstract void resizeShape(float x, float y);
    public abstract void drawShape(Canvas canvas, Paint paint, Resources resources);

}
