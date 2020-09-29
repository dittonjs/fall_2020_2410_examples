package com.example.telephone;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

public class PhoneButton extends AppCompatButton {
    public PhoneButton(Context context, PhoneButtonData data, View.OnClickListener onClick) {
        super(context);
        setText(data.getButtonText());
        setOnClickListener(onClick);
    }
}
