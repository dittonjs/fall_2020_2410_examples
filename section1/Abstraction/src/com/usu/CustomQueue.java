package com.usu;

import java.util.ArrayList;
import java.util.Queue;

public class CustomQueue {
    private ArrayList<String> personQueue = new ArrayList<>();

    public void enqueuePerson(String personName) {
        personQueue.add(personName);
    }

    public String dequeuePerson() {
       return personQueue.remove(0);
    }

    public int getQueueLength() {
        return personQueue.size();
    }
}
