package com.example.personalblog.components;

import android.content.Context;
import android.text.Editable;
import android.view.Gravity;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MaterialInput extends TextInputLayout {
    private TextInputEditText input;

    public MaterialInput(Context context, String hint) {
        this(context, hint, false);
    }

    public MaterialInput(Context context, String hint, Boolean isMultiLine) {
        super(context);
        LayoutParams params  = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(48, 24, 48, 24);
        setLayoutParams(params);
        setHint(hint);
        input = new TextInputEditText(getContext());
        input.setGravity(Gravity.START);
        if (isMultiLine) {
            input.setLines(4);
        } else {
            input.setSingleLine(true);
        }
        addView(input);
    }

    public String getText() {
        return input.getText().toString();
    }

    public void setText(String text) {
        input.setText(text);
    }
}
