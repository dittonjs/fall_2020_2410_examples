package com.example.mspaint;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.Shape;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;

public class ShapeSelector extends LinearLayout {

    private ShapeButton currentShapeButton;
    public enum ShapeType {
        LINE,
        CIRCLE,
        RECT,
        PLUS
    }

    private class ShapeButton extends AppCompatButton {
        private ShapeType type;
        public ShapeButton(Context context, ShapeType type, View.OnClickListener onClick) {
            super(context);
            this.type = type;
            setOnClickListener(onClick);
            GradientDrawable background = new GradientDrawable();
            background.setColor(getResources().getColor(R.color.colorPrimary, null));
            background.setStroke(5, getResources().getColor(R.color.colorAccent, null));
            setBackground(background);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            setTextColor(Color.WHITE);
            setLayoutParams(params);
        }

        public void select() {
            GradientDrawable background = new GradientDrawable();
            background.setColor(getResources().getColor(R.color.colorAccent, null));
            background.setStroke(5, getResources().getColor(R.color.colorAccent, null));
            setBackground(background);
        }

        public void deselect() {
            GradientDrawable background = new GradientDrawable();
            background.setColor(getResources().getColor(R.color.colorPrimary, null));
            background.setStroke(5, getResources().getColor(R.color.colorAccent, null));
            setBackground(background);
        }
    }

    public ShapeSelector(Context context) {
        super(context);

        for (ShapeType type: ShapeType.values()) {
            ShapeButton button = new ShapeButton(
                    context,
                    type,
                    view -> {
                        currentShapeButton.deselect();
                        currentShapeButton = (ShapeButton) view;
                        currentShapeButton.select();
                    }
            );
            button.setText(type.toString());
            if (type == ShapeType.CIRCLE) {
                button.select();
                currentShapeButton = button;
            }

            addView(button);
        }

    }

    public ShapeType getCurrentType() {
        return currentShapeButton.type;
    }
}
