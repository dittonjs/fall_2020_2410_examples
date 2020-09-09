package com.usu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class UIThread implements Runnable {
    public Queue<String> eventQueue = new LinkedList<>();
    public ArrayList<CustomEventHandler> eventHandlers = new ArrayList<>();
    private boolean running = false;

    public void run() {
        while(running) {
            while (true) {
                String event = eventQueue.poll();
                if (event == null) break;
                for (CustomEventHandler eventHandler: eventHandlers) {
                    eventHandler.handleEvent(event);
                }
            }
            synchronized (eventQueue) {
                try {
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void stop() {
        running = false;
        synchronized (eventQueue) {
            eventQueue.notify();
        }
    }

    public void sendEvent(String event) {
        synchronized (eventQueue) {
            eventQueue.add(event);
            eventQueue.notify();
        }
    }

    public void addEventHandler(CustomEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }
}
