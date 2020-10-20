package com.example.profilepage;

public class Person extends Model {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int id;

    public Person(String firstName, String lastName, String email, String phoneNumber, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setEmail(String email) {
        this.email = email;
//        this.saveInDatabase();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
//        this.saveInDatabase();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
//        this.saveInDatabase();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
//        this.saveInDatabase();
    }

    public int getId() {
        return id;
    }
}
