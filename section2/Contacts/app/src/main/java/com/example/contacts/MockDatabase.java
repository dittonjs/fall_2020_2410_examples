package com.example.contacts;

import java.util.ArrayList;

public class MockDatabase {
    public static ArrayList<Contact> getContacts(int amount) {
        ArrayList<Contact> contacts = new ArrayList<>();

        for(int i = 0; i < amount; i++) {
            contacts.add(new Contact("Person " + i, "000" + i));
        }

        return contacts;
    }
}
