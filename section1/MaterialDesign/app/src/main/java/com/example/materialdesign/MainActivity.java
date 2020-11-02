package com.example.materialdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout contentLayout = new LinearLayout(this);
//        contentLayout.setOrientation();
        FrameLayout mainLayout = new FrameLayout(this);


        AppCompatButton button = new AppCompatButton(this);
        button.setText("I am a regular button");

//        MaterialButton materialButton = new MaterialButton(
//                this,
//                null,
//                R.style.Widget_MaterialComponents_Button_TextButton
//        );
//        materialButton.setText("I am a material button");

        FloatingActionButton fab = new FloatingActionButton(this);
        fab.setImageResource(R.drawable.ic_baseline_add_24);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = (Gravity.BOTTOM | Gravity.RIGHT);
        params.setMargins(0, 0, 48, 48);
        fab.setLayoutParams(params);
        mainLayout.addView(contentLayout);
        mainLayout.addView(fab);

        contentLayout.addView(button);
        setContentView(mainLayout);
    }
}