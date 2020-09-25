package com.example.contacts;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;

public class ContactView extends LinearLayout {
    public ContactView(Context context, Contact contact) {
        super(context);
        setOrientation(VERTICAL);
        AppCompatTextView nameView = new AppCompatTextView(context);
        nameView.setText(contact.getName());
        addView(nameView);
        nameView.setTextSize(18);

        AppCompatTextView phoneNumberView = new AppCompatTextView(context);
        phoneNumberView.setText(contact.getPhoneNumber());
        addView(phoneNumberView);

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(0, 24, 0, 0);
        setLayoutParams(params);
    }
}
