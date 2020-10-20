package com.example.profilepage;

public class Person extends Model{
    private String firstName;
    private String lastName;
    private int id;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
//        saveToDatabase();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
//        saveToDatabase();
    }
}
