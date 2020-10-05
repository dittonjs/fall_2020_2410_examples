package com.example.telephone;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.GridLayout;

import androidx.appcompat.widget.AppCompatButton;

public class PhoneButton extends AppCompatButton {
    public PhoneButton(Context context, PhoneButtonData data, View.OnClickListener onClick) {
        super(context);
        setText(data.getButtonText());
        setOnClickListener(onClick);
        GradientDrawable background = new GradientDrawable();

        background.setColor(getResources().getColor(R.color.colorPrimary, null));
        background.setStroke(2, Color.BLACK);
        background.setCornerRadius(400);
        setBackground(background);
        setTextSize(32);
        setTextColor(Color.WHITE);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.setMargins(12,12,12,12);
        params.rowSpec = GridLayout.spec(data.getRow(), data.getSize(), 1);
        params.columnSpec = GridLayout.spec(data.getCol(), data.getSize(), 1);
        setLayoutParams(params);
        setOutlineProvider(ViewOutlineProvider.BACKGROUND);
        setElevation(800);

    }
}
