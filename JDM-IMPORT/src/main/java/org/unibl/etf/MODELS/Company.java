package org.unibl.etf.MODELS;

public class Company extends Customer{
    private String name;

    public Company() {
        super();
    }

    public Company(int id, String name, String email, String phone) {
        super(id, email, phone);
        this.name = name;
    }

    public Company(String name, String email, String phone) {
        super(email, phone);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name;
    }
}
