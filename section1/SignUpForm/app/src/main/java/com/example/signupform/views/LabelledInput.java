package com.example.signupform.views;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.Layout;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

public class LabelledInput extends LinearLayout {
    private AppCompatEditText input;
    public LabelledInput(Context context, String labelText) {
        super(context);
//        setOrientation(VERTICAL);
        AppCompatTextView label = new AppCompatTextView(context);
        label.setText(labelText);
        label.setTextSize(18);
//        LayoutParams labelParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        labelParams.weight = 1;
//        label.setLayoutParams(labelParams);
        this.input = new AppCompatEditText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.weight = 1;
        input.setLayoutParams(params);

        AppCompatTextView label2 = new AppCompatTextView(context);
        label2.setText(labelText);
        label2.setTextSize(18);
        addView(label);
        addView(input);
        addView(label2);
        label2.setGravity(Gravity.RIGHT);
        label2.setBackgroundColor(Color.GREEN);
    }

    public Editable getText() {
        return input.getText();
    }
}
