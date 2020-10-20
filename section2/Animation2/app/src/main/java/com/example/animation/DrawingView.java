package com.example.animation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawingView extends View {
    Paint paint = new Paint();
    int radius = 0;
    int maxR = 200;
    int x = 0;
    int maxX = 700;
    public DrawingView(Context context) {
        super(context);
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(3000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float percentage = (float)valueAnimator.getAnimatedValue();
                x = (int)(percentage * maxX);
                radius = (int)(percentage * maxR);
                invalidate();
            }
        });
        animator.start();

        ValueAnimator colorAnimator = ValueAnimator.ofInt(0, 255);
        colorAnimator.setRepeatMode(ValueAnimator.REVERSE);
        colorAnimator.setRepeatCount(ValueAnimator.INFINITE);
        colorAnimator.setDuration(3000);
        colorAnimator.addUpdateListener(valueAnimator -> {
            paint.setColor(Color.rgb((int)valueAnimator.getAnimatedValue(), 100, 100));
            invalidate();
        });

        colorAnimator.start();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(x, getHeight() / 2, radius, paint);
    }
}
