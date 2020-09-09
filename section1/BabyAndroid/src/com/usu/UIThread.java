package com.usu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class UIThread implements Runnable {
    private boolean running = false;
    private Queue<String> eventQueue = new LinkedList<>();
    private ArrayList<CustomEventListener> eventListeners = new ArrayList<>();
    public void run() {
        while(running) {
            while(true) {
                String event = eventQueue.poll();
                if (event == null) break;
                for(CustomEventListener eventListener : eventListeners) {
                    eventListener.handleEvent(event);
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

    public void addEventListener(CustomEventListener eventListener) {
        eventListeners.add(eventListener);
    }
}
