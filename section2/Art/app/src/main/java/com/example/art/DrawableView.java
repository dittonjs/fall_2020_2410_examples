package com.example.art;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowMetrics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

public class DrawableView extends View {
    Paint paint = new Paint();
    public DrawableView(Context context) {
        super(context);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        AppCompatActivity activity = (AppCompatActivity)getContext();
//        WindowMetrics metrics = activity.getWindowManager().getCurrentWindowMetrics();
//        canvas.drawCircle(100, 200, 100, paint);
//        canvas.drawLine(400, 400, 800, 500, paint);
//        canvas.drawRect(100, 600, 500, 900, paint);
        invalidate();
    }
}
