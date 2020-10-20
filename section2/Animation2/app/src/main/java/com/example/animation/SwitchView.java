package com.example.animation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SwitchView extends View {
    Paint paint = new Paint();
    int height = 120;
    boolean isOn = false;
    int currentX = 60;
    ValueAnimator animator;

    public SwitchView(Context context) {
        super(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height);
        setLayoutParams(params);
        setOnClickListener(view -> {
            isOn = !isOn;
            startAnimation();
            invalidate();
        });
    }

    private void startAnimation() {
        if (animator != null) {
            animator.cancel();
        }
        animator = ValueAnimator.ofInt(currentX, isOn ? 160 : 60);
        animator.setDuration(80);
        animator.addUpdateListener(valueAnimator -> {
            currentX = (int)valueAnimator.getAnimatedValue();
            invalidate();
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        canvas.drawRoundRect(10, 10, 210, 110, 50, 50, paint);
        paint.reset();
        

        paint.setColor(getResources().getColor(R.color.colorAccent, null));
        canvas.drawRoundRect(10, 10, currentX + 50, 110, 50, 50, paint);
        paint.reset();
        canvas.drawCircle(currentX, 60, 50, paint);
    }
}
