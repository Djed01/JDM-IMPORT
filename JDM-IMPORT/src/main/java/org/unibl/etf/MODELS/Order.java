package org.unibl.etf.MODELS;

public class Order {
    private int id;
    private Customer customer;
    private Car car;
    private String date;
    private String deliveryDate;
    private double quantity;
    private double orderTotal;

    public Order() {
        super();
    }

    public Order(int id, Customer customer, Car car, String date, String deliveryDate, double quantity,
            double orderTotal) {
        super();
        this.id = id;
        this.customer = customer;
        this.car = car;
        this.date = date;
        this.deliveryDate = deliveryDate;
        this.quantity = quantity;
        this.orderTotal = orderTotal;
    }

    public Order(Customer customer, Car car, String date, String deliveryDate, double quantity,
            double orderTotal) {
        super();
        this.customer = customer;
        this.car = car;
        this.date = date;
        this.deliveryDate = deliveryDate;
        this.quantity = quantity;
        this.orderTotal = orderTotal;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", car=" + car +
                ", date='" + date + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", quantity=" + quantity +
                ", orderTotal=" + orderTotal +
                '}';
    }
}
