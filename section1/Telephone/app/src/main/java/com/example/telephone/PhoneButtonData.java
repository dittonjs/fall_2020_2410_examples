package com.example.telephone;

import android.widget.Button;

public class PhoneButtonData {
    enum ButtonType {
        NUMBER,
        CALL
    }
    private String buttonText;
    private int row;
    private int col;
    private int size;
    private ButtonType type;

    public PhoneButtonData(String buttonText, int row, int col, int size) {
        this.buttonText = buttonText;
        this.row = row;
        this.col = col;
        this.size = size;
        this.type = ButtonType.NUMBER;
    };

    public PhoneButtonData(String buttonText, int row, int col, int size, ButtonType type) {
        this.buttonText = buttonText;
        this.row = row;
        this.col = col;
        this.size = size;
        this.type = type;
    };

    public String getButtonText() {
        return buttonText;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getSize() {
        return size;
    }

    public ButtonType getType() {
        return type;
    }
}
