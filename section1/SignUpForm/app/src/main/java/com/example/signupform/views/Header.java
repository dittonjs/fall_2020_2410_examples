package com.example.signupform.views;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;

public class Header extends AppCompatTextView {
    public Header(Context context) {
        super(context);
        setTextSize(32);
        setTextColor(Color.BLACK);
//        setBackgroundColor(Color.RED);
//        setPadding(40, 40, 40, 40);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 40, 0, 0);
//        setGravity(Gravity.RIGHT);
        setLayoutParams(params);
    }
}
