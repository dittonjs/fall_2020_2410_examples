package com.example.art;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.Shape;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;

public class ShapeSelector extends LinearLayout {
    ShapeButton selectedButton;

    ArrayList<ShapeButton> buttons = new ArrayList<ShapeButton>();

    private class ShapeButton extends AppCompatButton {
        public ShapeButton(Context context) {
            super(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            setLayoutParams(params);
            GradientDrawable background = new GradientDrawable();
            background.setColor(getResources().getColor(R.color.colorPrimary, null));
            background.setStroke(4, Color.BLACK);
            setTextColor(Color.WHITE);
            setBackground(background);
        }

        public void select() {
            GradientDrawable background = new GradientDrawable();
            background.setColor(getResources().getColor(R.color.colorAccent, null));
            background.setStroke(4, Color.BLACK);
            setTextColor(Color.WHITE);
            setBackground(background);
        }

        public void deselect() {
            GradientDrawable background = new GradientDrawable();
            background.setColor(getResources().getColor(R.color.colorPrimary, null));
            background.setStroke(4, Color.BLACK);
            setTextColor(Color.WHITE);
            setBackground(background);
        }
    }

    public ShapeSelector(Context context) {
        super(context);
        ShapeButton circleButton = new ShapeButton(context);
        circleButton.setText("CIRCLE");
        ShapeButton rectangleButton = new ShapeButton(context);
        rectangleButton.setText("RECTANGLE");
        ShapeButton lineButton = new ShapeButton(context);
        lineButton.setText("LINE");
        ShapeButton triangleButton = new ShapeButton(context);
        triangleButton.setText("TRIANGLE");
        buttons.add(circleButton);
        buttons.add(rectangleButton);
        buttons.add(lineButton);
        buttons.add(triangleButton);

        buttons.forEach(button -> {
            button.setOnClickListener(view -> {
                selectedButton.deselect();
                selectedButton = (ShapeButton) view;
                selectedButton.select();
            });
            addView(button);
        });

        selectedButton = circleButton;
        selectedButton.select();
    }


}
