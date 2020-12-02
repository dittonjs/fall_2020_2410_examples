package com.example.personalblog.components;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.personalblog.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MaterialInput extends TextInputLayout {
    TextInputEditText input;

    public MaterialInput(Context context, String hint) {
        this(context, hint, false);
    }

    public MaterialInput(@NonNull Context context, String hint, boolean isMultiLine) {
        super(context, null, R.attr.textInputStyle);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(48, 24, 48, 24);
        setLayoutParams(params);
        input = new TextInputEditText(getContext());
        if (isMultiLine) {
            input.setLines(5);
        } else {
            input.setSingleLine(true);
        }
        input.setHint(hint);
        input.setGravity(Gravity.START);
        addView(input);
    }

    public Editable getText() {
        return input.getText();
    }

    public void setText(String text) {
        input.setText(text);
    }

}
