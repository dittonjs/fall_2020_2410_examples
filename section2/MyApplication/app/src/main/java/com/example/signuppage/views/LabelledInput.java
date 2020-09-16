package com.example.signuppage.views;

import android.content.Context;
import android.text.Editable;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

public class LabelledInput extends LinearLayout {
    private AppCompatEditText input;
    public LabelledInput(Context context, String labelText) {
        super(context);
        setOrientation(VERTICAL);
        AppCompatTextView label = new AppCompatTextView(context);
        label.setText(labelText);
        label.setTextSize(18);
        this.input = new AppCompatEditText(context);
        addView(label);
        addView(input);
    }

    public Editable getText() {
        return input.getText();
    }

}
