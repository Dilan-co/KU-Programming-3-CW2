package com.models;

import com.Utils.OrderStatus;

public class Order {
    private Pizza pizza;
    private String customerName;
    private String address;
    private OrderStatus status;

    public Order(Pizza pizza, String customerName, String address) {
        this.pizza = pizza;
        this.customerName = customerName;
        this.address = address;
        this.status = OrderStatus.PLACED;
    }

    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }

    public String getOrderDetails() {
        return "Order for: " + customerName + "\nAddress: " + address + "\nPizza: " + pizza + "\nStatus: " + status;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
