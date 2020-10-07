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
    public enum ShapeType {
        CIRCLE,
        RECT,
        LINE
    }
    private ShapeButton selectedButton;

    private ArrayList<ShapeButton> buttons = new ArrayList<ShapeButton>();

    private class ShapeButton extends AppCompatButton {
        private ShapeType type;
        public ShapeButton(Context context, ShapeType type) {
            super(context);
            this.type = type;
            setText(type.toString());
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

        public ShapeType getType() {
            return type;
        }
    }

    public ShapeSelector(Context context) {
        super(context);
        ShapeButton circleButton = new ShapeButton(context, ShapeType.CIRCLE);
        ShapeButton rectangleButton = new ShapeButton(context, ShapeType.RECT);
        ShapeButton lineButton = new ShapeButton(context, ShapeType.LINE);

        buttons.add(circleButton);
        buttons.add(rectangleButton);
        buttons.add(lineButton);

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

    public ShapeType getSelectedType() {
        return selectedButton.getType();
    }
}
