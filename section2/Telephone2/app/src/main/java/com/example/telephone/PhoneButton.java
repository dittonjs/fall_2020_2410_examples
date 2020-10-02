package com.example.telephone;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public class PhoneButton extends AppCompatButton {
    public PhoneButton(Context context, PhoneButtonData data, View.OnClickListener onClick) {
        super(context);
        setText(data.getButtonText());
        setOnClickListener((view) -> {
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(Color.DKGRAY);
            drawable.setStroke(2, Color.BLACK);
            setBackground(drawable);
            onClick.onClick(view);
        });

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int color = getResources().getColor(R.color.colorPrimary, null);
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    GradientDrawable drawable = new GradientDrawable();
                    drawable.setColor(getResources().getColor(R.color.colorHighlight, null));
                    drawable.setStroke(2, Color.BLACK);
                    drawable.setCornerRadius(100);
                    setBackground(drawable);
                    onClick.onClick(view);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    GradientDrawable drawable = new GradientDrawable();
                    drawable.setColor(color);
                    drawable.setStroke(2, Color.BLACK);
                    drawable.setCornerRadius(100);
                    setBackground(drawable);
                }
                return true;
            }
        });

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.rowSpec = GridLayout.spec(data.getRow(), 1, 1);
        params.columnSpec = GridLayout.spec(data.getColumn(), data.getSize(), 1);
        params.setMargins(24,24,24,24);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
        drawable.setStroke(2, Color.BLACK);
        drawable.setCornerRadius(100);

        setTextSize(32);
        setTextColor(Color.WHITE);
        setBackground(drawable);
        setLayoutParams(params);
    }

}
