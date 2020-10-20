package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        SwitchView switchView = new SwitchView(this);
        switchView.setText("Notifications");
        mainLayout.addView(switchView);

        SwitchView switchView2 = new SwitchView(this);
        switchView2.setText("Allow Location");
        mainLayout.addView(switchView2);

        setContentView(mainLayout);
    }
}