package com.example.telephone;

public class PhoneButtonData {
    private String buttonText;
    private int column;
    private int row;
    private int size;

    public PhoneButtonData(String buttonText, int row, int column, int size) {
        this.buttonText = buttonText;
        this.column = column;
        this.row = row;
        this.size = size;
    }

    public String getButtonText() {
        return buttonText;
    }

    public int getSize() {
        return size;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
