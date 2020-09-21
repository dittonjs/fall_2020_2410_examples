package com.usu;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int MAX_QUEUE_LENGTH = 10;
        ArrayList<String> personNames = new ArrayList<>();

        for (int i = 0; i < 40; i ++) {
            personNames.add("Person " + i);
        }

        CustomQueue queue = new CustomQueue();

        while (true) {
            if (personNames.size() == 0) break;

            String personName = personNames.remove(0);

            if (queue.getQueueLength() == MAX_QUEUE_LENGTH) {
                String dequeuedPersonName = queue.dequeuePerson();
                System.out.println(dequeuedPersonName + " had their soul eaten by the DVM and went home");
            }
            queue.enqueuePerson(personName);
            System.out.println(personName + " begrudgingly got in line at the DMV");
        }
    }
}
