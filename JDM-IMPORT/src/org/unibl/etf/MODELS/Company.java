package org.unibl.etf.MODELS;

public class Company extends Customer{
    private String name;

    public Company() {
        super();
    }

    public Company(int id, String email, String phone, String name) {
        super(id, email, phone);
        this.name = name;
    }

    public Company(String email, String phone, String name) {
        super(email, phone);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
