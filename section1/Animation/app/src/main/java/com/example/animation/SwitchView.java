package com.example.animation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.renderscript.Sampler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SwitchView extends View {
    private boolean isOn = false;
    private String text = "";

    private Paint paint = new Paint();
    private int height = 120;
    private int currentX = 60;
    private ValueAnimator animator;
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
        animator = ValueAnimator.ofInt(currentX, isOn ? 210 : 60);
        animator.setDuration(100);
        animator.addUpdateListener(valueAnimator -> {
            currentX = (int) valueAnimator.getAnimatedValue();
            invalidate();
        });
        animator.start();
    }

    public void setText(String text) {
        this.text = text;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawRoundRect(10, 10, 260, 110, 50, 50, paint);
        paint.reset();

        paint.setColor(getResources().getColor(R.color.colorAccent, null));
        canvas.drawRoundRect(10, 10, currentX + 50, 110, 60, 60, paint);
        paint.reset();
        canvas.drawCircle(currentX, 60, 50, paint);

        paint.setTextSize(40);
        canvas.drawText(text, 280, getHeight() / 2 + 20, paint);
    }
}
