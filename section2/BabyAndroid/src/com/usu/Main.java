package com.usu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String event = "";
        UIThread uiThread = new UIThread();
        uiThread.start();
        uiThread.addEventHandler(new CustomEventHandler() {
            @Override
            public void handleEvent(String event) {
                if (event.equals("touch")) {
                    System.out.println("You touched the screen! Yippee!");
                }
            }
        });

        uiThread.addEventHandler(new CustomEventHandler() {
            @Override
            public void handleEvent(String event) {
                if (event.equals("tap")) {
                    System.out.println("You tapped the screen! Yippee!");
                }
            }
        });

        uiThread.addEventHandler(new CustomEventHandler() {
            @Override
            public void handleEvent(String event) {
                if (event.equals("shake")) {
                    System.out.println("You shook the screen! Yippee!");
                }
            }
        });
        while(!event.equals("stop")) {
            Scanner scanner = new Scanner(System.in);
            event = scanner.nextLine();
            uiThread.sendEvent(event);
        }
        uiThread.stop();
    }
}
