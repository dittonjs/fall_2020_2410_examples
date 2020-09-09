package com.usu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int count = 0;
        while (count != -1) {
            Scanner scanner = new Scanner(System.in);
            count = scanner.nextInt();
            new CustomCounter(count);
        }

        System.out.println("Goodbye!");
    }
}
