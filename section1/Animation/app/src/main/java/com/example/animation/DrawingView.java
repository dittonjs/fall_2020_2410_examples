package com.example.animation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class DrawingView extends View {
    Paint paint = new Paint();
    int radius = 200;
    int maxRadius = 200;
    int x = 100;
    int maxX = 600;
    ValueAnimator animator;
    ValueAnimator colorAnimator;
    public DrawingView(Context context) {
        super(context);
        animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setDuration(3000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(valueAnimator -> {
           float percentage = (float)valueAnimator.getAnimatedValue();
           x = (int)(maxX * percentage + 100);
           radius = (int)(maxRadius * percentage + 10);
           invalidate();
        });
        animator.start();

        colorAnimator = ValueAnimator.ofInt(0, 255);
        colorAnimator.setRepeatMode(ValueAnimator.REVERSE);
        colorAnimator.setDuration(3000);
        colorAnimator.setRepeatCount(ValueAnimator.INFINITE);
        colorAnimator.addUpdateListener(valueAnimator -> {
            paint.setColor(Color.rgb(
                    (int) valueAnimator.getAnimatedValue(),
                    100,
                    100
            ));
            invalidate();
        });
        colorAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(x, getHeight() / 2, radius, paint);
        canvas.drawCircle(10, 10, 20, paint);
    }
}
