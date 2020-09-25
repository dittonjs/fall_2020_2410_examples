package com.example.gridlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GridLayout gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(3);

        for (int row = 0; row < 3; row ++ ) {
            for (int col = 0; col <  3; col ++) {
                
                AppCompatButton button = new AppCompatButton(this);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                GradientDrawable drawable = new GradientDrawable();
                int[] colors = {Color.GRAY, Color.DKGRAY};
                drawable.setOrientation(GradientDrawable.Orientation.TL_BR);
                drawable.setColors(colors);
                drawable.setStroke(20, Color.BLACK);
                button.setBackground(drawable);
//                params.setMargins(24, 24, 24, 24);
                params.rowSpec = GridLayout.spec(row, 1, 1);
                params.columnSpec = GridLayout.spec(col, 1, 1);
                button.setLayoutParams(params);
                button.setText("View " + row + " " + col);
                button.setGravity(Gravity.CENTER);
                gridLayout.addView(button);
            }

        }

        setContentView(gridLayout);
    }
}