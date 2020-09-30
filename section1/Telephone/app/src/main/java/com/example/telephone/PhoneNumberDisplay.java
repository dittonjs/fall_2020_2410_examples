package com.example.telephone;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.GridLayout;

import androidx.appcompat.widget.AppCompatTextView;

public class PhoneNumberDisplay extends AppCompatTextView {
    public PhoneNumberDisplay(Context context) {
        super(context);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.rowSpec = GridLayout.spec(0, 1, 1);
        params.columnSpec = GridLayout.spec(0, 3, 1);
        setText(getResources().getString(R.string.instructions));
        setGravity(Gravity.CENTER);
        setTextSize(24);
        setTextColor(Color.WHITE);
        setLayoutParams(params);
    }
}
