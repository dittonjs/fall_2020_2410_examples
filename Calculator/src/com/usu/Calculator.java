package com.usu;

import java.util.ArrayList;

public class Calculator {

    // tokens should be space separted
    public static double evaluate(String expression) {
        // tokens
        // values = any real number. Eg 1, 3.2, 5968.3
        // operators = one of (+ - / *)
        // 2 + 3 + 4
        String[] tokens = expression.split(" ");
        if (tokens.length == 0) {
            // we have an error and we need to handle it
            return Double.NaN;
        }

        if (tokens.length == 1) {
            try {
                return Double.parseDouble(tokens[0]);
            } catch (Exception e) {
                return Double.NaN;
            }
        }

        if (tokens.length == 2) {
            // we also have an error
            return Double.NaN;
        }
        double currentValue;
        try {
            currentValue = Double.parseDouble(tokens[0]);
        } catch (Exception e) {
            return Double.NaN;
        }
        String operation = tokens[1];
        String mode = "value";

        for (int i = 2; i < tokens.length; i ++) {
            if (mode.equals("value")) {
                double foundValue;
                try {
                    foundValue = Double.parseDouble(tokens[i]);
                } catch (Exception e) {
                    return Double.NaN;
                }

                if (operation.equals("+")) {
                    currentValue = currentValue + foundValue;
                }
                if (operation.equals("-")) {
                    currentValue = currentValue - foundValue;
                }
                if (operation.equals("/")) {
                    currentValue = currentValue / foundValue;
                }
                if (operation.equals("*")) {
                    currentValue = currentValue * foundValue;
                }
                mode = "operator";
            } else {
                operation = tokens[i];
                mode = "value";
            }
        }

        return currentValue;
    }
}
