package com.example.art;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Image extends CustomShape{
    private float x2;
    private float y2;
    public Image(float x, float y, float x2, float y2) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void drawShape(Canvas canvas, Paint paint, Resources resources) {

//                (x, y, x2, y2, paint);
    }

    @Override
    public void resizeShape(float x, float y) {
        this.x2 = x;
        this.y2 = y;
    }
}
