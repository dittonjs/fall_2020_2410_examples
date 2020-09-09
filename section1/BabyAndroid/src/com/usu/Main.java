package com.usu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String event = "";
        UIThread uiThread = new UIThread();
        uiThread.start();
        System.out.println("Welcome to Baby Android!");
        uiThread.addEventListener(new CustomEventListener() {
            @Override
            public void handleEvent(String event) {
                if (event.equals("touch")) {
                    System.out.println("You touch the screen!");
                }
            }
        });

        uiThread.addEventListener(new CustomEventListener() {
            @Override
            public void handleEvent(String event) {
                if (event.equals("swipe")) {
                    System.out.println("You swiped the screen!");
                }
            }
        });
        while (!event.equals("stop")) {
            Scanner scanner = new Scanner(System.in);
            event = scanner.nextLine();
            uiThread.sendEvent(event);
        }
        uiThread.stop();
    }
}
