package org.unibl.etf.MODELS;

public abstract class Customer {
    private int id;
    private String email;
    private String phone;

    public Customer() {
        super();
    }

    public Customer(int id, String email, String phone) {
        super();
        this.id = id;
        this.email = email;
        this.phone = phone;
    }

    public Customer(String email, String phone) {
        super();
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
