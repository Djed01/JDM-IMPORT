package org.unibl.etf.MODELS;

public class Individual extends Customer{
    private String firstName;
    private String lastName;

    public Individual() {
        super();
    }

    public Individual(int id, String email, String phone, String firstName, String lastName) {
        super(id, email, phone);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Individual(String email, String phone, String firstName, String lastName) {
        super(email, phone);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
