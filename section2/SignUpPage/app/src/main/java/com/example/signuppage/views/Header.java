package com.example.signuppage.views;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;

public class Header extends AppCompatTextView {
    public Header(Context context) {
        super(context);
        setTextSize(32);
        setTextColor(Color.BLACK);
        setBackgroundColor(Color.RED);
//        setGravity(Gravity.RIGHT);
//        setPadding(40, 400 ,40 , 40);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        setGravity(Gravity.CENTER);
        params.setMargins(200, 200, 200, 0);
        setLayoutParams(params);
    }
}
