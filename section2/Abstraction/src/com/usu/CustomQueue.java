package com.usu;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Queue;

public class CustomQueue {
    private ArrayList<String> personQueue = new ArrayList<>();

    public void enqueuePerson(String personName) {
        personQueue.add(personName);
    }

    public int getQueueLength() {
        return personQueue.size();
    }

    public String dequeuePerson() {
        return personQueue.remove(0);
    }
}
