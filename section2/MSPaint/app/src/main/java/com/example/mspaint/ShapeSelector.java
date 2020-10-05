package com.example.mspaint;

import android.content.Context;
import android.graphics.drawable.shapes.Shape;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;

public class ShapeSelector extends LinearLayout {

    ShapeButton currentShapeButton;
    public enum ShapeType {
        LINE,
        CIRCLE,
        RECT
    }

    private class ShapeButton extends AppCompatButton {
        private ShapeType type;
        public ShapeButton(Context context, ShapeType type, View.OnClickListener onClick) {
            super(context);
            this.type = type;
            setOnClickListener(onClick);
        }
    }

    public ShapeSelector(Context context) {
        super(context);

        for (ShapeType type: ShapeType.values()) {
            ShapeButton button = new ShapeButton(
                    context,
                    type,
                    view -> {
                        ShapeButton shapeButton = (ShapeButton) view;
                    }
            );
            button.setText(type.toString());
            addView(button);
        }
    }
}
