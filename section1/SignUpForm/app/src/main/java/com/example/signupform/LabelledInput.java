package com.example.signupform;

import android.content.Context;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

public class LabelledInput extends LinearLayout {
    public LabelledInput(Context context, String labelText) {
        super(context);
        setOrientation(VERTICAL);
        AppCompatTextView label = new AppCompatTextView(context);
        label.setText(labelText);
        AppCompatEditText input = new AppCompatEditText(context);
        addView(label);
        addView(input);
    }
}
