package com.example.mspaint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        ShapeSelector shapeSelector = new ShapeSelector(this);
        linearLayout.addView(shapeSelector);

//        OnDrawTest view = new OnDrawTest(this);
        setContentView(linearLayout);
    }
}