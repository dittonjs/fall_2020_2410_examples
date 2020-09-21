package com.usu;

import java.util.ArrayList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
	    // write your code here
        final int MAX_QUEUE_LENGTH = 10;
        CustomQueue queue = new CustomQueue();
        ArrayList<String> peopleNames = new ArrayList<>();
        for (int i = 0; i < 40; i ++) {
            peopleNames.add("Person " + i);
        }

        while(true) {
            if (peopleNames.size() == 0) break;
            String personName = peopleNames.remove(0);

            if (queue.getQueueLength() < MAX_QUEUE_LENGTH) {
                queue.enqueuePerson(personName);
                System.out.println("Enqueued " + personName);
            } else {
                System.out.println("Queue is full we will process someone in the queue");
                String removedPersonName = queue.dequeuePerson();
                System.out.println("Dequeued " + removedPersonName);
                queue.enqueuePerson(personName);
                System.out.println("Enqueued " + personName);
            }
        }

    }
}
