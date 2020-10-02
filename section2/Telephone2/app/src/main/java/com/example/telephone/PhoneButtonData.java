package com.example.telephone;

public class PhoneButtonData {
    enum ButtonType {
        INPUT,
        CALL,
    }
    private String buttonText;
    private int column;
    private int row;
    private int size;
    private ButtonType type;

    public PhoneButtonData(String buttonText, int row, int column, int size) {
        this.buttonText = buttonText;
        this.column = column;
        this.row = row;
        this.size = size;
        this.type = ButtonType.INPUT;
    }

    public PhoneButtonData(String buttonText, int row, int column, int size, ButtonType type) {
        this.buttonText = buttonText;
        this.column = column;
        this.row = row;
        this.size = size;
        this.type = type;
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

    public ButtonType getType() {
        return type;
    }
}
