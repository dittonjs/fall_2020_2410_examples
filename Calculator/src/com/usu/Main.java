package com.usu;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String expression = "";
        Scanner scanner = new Scanner(System.in);
        while (!expression.equals("stop")) {
            expression = scanner.nextLine().trim();
            if (!expression.equals("stop")) {
                double result = Calculator.evaluate(expression);
                System.out.println("" + result);
            }
        }
    }
}
// delimiters = a space delimiter

// tokens
// values = any real number. Eg 1, 3.2, 5968.3, -5
// operators = one of (+ - / *)

// valid expressions
// value eg. 1, 2, 23.4, -5
// value | operator | value eg 3 + 2, 5.6 + 9.2
// expression | operator | value eg 1 + 2 + 5, 1 + 3 + 4.3 + 5 + 3.2

// this is an expression
// 1 + 2 + 3 * 4
