package com.example.framelayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout mainLayout = new FrameLayout(this);
        AppCompatImageView imageView = new AppCompatImageView(this);
        imageView.setImageResource(R.drawable.ic_launcher_foreground);

        AppCompatButton button = new AppCompatButton(this);
        button.setText("Press me!!!!");

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = (Gravity.BOTTOM | Gravity.RIGHT);
        params.setMargins(0, 0, 48, 48);
        button.setLayoutParams(params);

        mainLayout.addView(imageView);
        mainLayout.addView(button);
        setContentView(mainLayout);
    }
}