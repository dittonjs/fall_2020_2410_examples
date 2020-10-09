package com.example.mspaint;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

public class PlusShape extends Shape {
    float r;
    Rectangle horizontal;
    Rectangle vertical;

    public PlusShape(float x, float y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;
        horizontal = new Rectangle(x - r, y - 40, x + r, y + 40);
        vertical = new Rectangle(x - 40, y - r, x + 40, y + r);
    }

    @Override
    public void drawShape(Canvas canvas, Paint paint, Resources resources) {
        horizontal.drawShape(canvas, paint, resources);
        vertical.drawShape(canvas, paint, resources);
    }

    @Override
    public void resizeShape(float x, float y) {
        r = Math.abs(x - this.x);
        horizontal = new Rectangle(this.x - r, this.y - 40, this.x + r, this.y + 40);
        vertical = new Rectangle(this.x - 40, this.y - r, this.x + 40, this.y + r);
    }
}
