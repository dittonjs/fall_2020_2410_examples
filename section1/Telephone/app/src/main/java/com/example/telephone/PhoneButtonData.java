package com.example.telephone;

public class PhoneButtonData {
    private String buttonText;
    private int row;
    private int col;
    private int size;

    public PhoneButtonData(String buttonText, int row, int col, int size) {
        this.buttonText = buttonText;
        this.row = row;
        this.col = col;
        this.size = size;
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
}
