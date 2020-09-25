package com.example.contacts;

import android.content.Context;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;

public class ContactView extends LinearLayout {
    public ContactView(Context context, Contact contact) {
        super(context);
        setOrientation(VERTICAL);
        AppCompatTextView nameView = new AppCompatTextView(context);
        nameView.setText(contact.getName());
        nameView.setTextSize(24);
        addView(nameView);

        AppCompatTextView phoneNumberView = new AppCompatTextView(context);
        phoneNumberView.setText(contact.getPhoneNumber());
        addView(phoneNumberView);


    }
}
