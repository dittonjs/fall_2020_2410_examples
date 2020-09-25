package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Contact> contacts = MockDatabase.getContacts(12000);
        LinearLayout mainLayout = new LinearLayout(this);
        ScrollView scrollView = new ScrollView(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        for(Contact contact: contacts) {
            mainLayout.addView(new ContactView(this, contact));
        }

        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                Log.d("ScrollView", "You scrollin'!!!!!");
            }
        });
        scrollView.addView(mainLayout);
        setContentView(scrollView);
    }
}