package com.example.mspaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

public class CustomButton extends AppCompatTextView {
    public CustomButton(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.drawCircle(100, 100, 50, new Paint());
    }
}
