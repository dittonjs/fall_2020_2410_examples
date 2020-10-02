package com.example.telephone;

import android.content.Context;
import android.view.Gravity;
import android.widget.GridLayout;

import androidx.appcompat.widget.AppCompatTextView;

public class PhoneNumberView extends AppCompatTextView {
    public PhoneNumberView(Context context) {
        super(context);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.rowSpec = GridLayout.spec(0, 1, 1);
        params.columnSpec = GridLayout.spec(0, 3, 1);
        setLayoutParams(params);
        setTextSize(32);
        setGravity(Gravity.CENTER);
    }
}
