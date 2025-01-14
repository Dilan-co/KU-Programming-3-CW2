package com.models;

import com.Utils.OrderStatus;

public class Order {
    private Pizza pizza;
    private String customerName;
    private String address;
    private String price;
    private OrderStatus status;

    public Order(Pizza pizza, String customerName, String address, double price) {
        this.pizza = pizza;
        this.customerName = customerName;
        this.address = address;
        this.price = String.valueOf(price);
        this.status = OrderStatus.PLACED;
    }

    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }

    public String getOrderDetails() {
        return "Order for: " + customerName + "\nAddress: " + address + "\nPizza: " + pizza +"\nPrice:  Rs. " + price+"0" + "\nStatus: " + status;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
