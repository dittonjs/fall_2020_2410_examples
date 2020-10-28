package com.example.signuppage.views;

import android.content.Context;
import android.text.Editable;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

public class LabelledInput extends LinearLayout {
    private AppCompatEditText input;
    public LabelledInput(Context context, String labelText) {
        super(context);
//        setOrientation(VERTICAL);
        AppCompatButton label = new AppCompatButton(context);
        label.setText("B");
//        label.setTextSize(18);
        LayoutParams labelParams = new LayoutParams(100, 100);
//        labelParams.weight = 0;
        label.setLayoutParams(labelParams);

        this.input = new AppCompatEditText(context);
        input.setLines(1);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        input.setLayoutParams(params);
        addView(label);
        addView(input);
        AppCompatButton label2 = new AppCompatButton(context);
        label2.setText("F");
        label2.setLayoutParams(labelParams);
//        label2.setTextSize(18);
        addView(label2);
    }

    public Editable getText() {
        return input.getText();
    }

}
