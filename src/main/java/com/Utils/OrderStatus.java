package com.Utils;

public enum OrderStatus {
    PLACED,
    PREPARING,
    OUT_FOR_DELIVERY,
    DELIVERED;

    @Override
    public String toString() {
        switch (this) {
            case PLACED:
                return "Order Placed";
            case PREPARING:
                return "Preparing Your Pizza";
            case OUT_FOR_DELIVERY:
                return "Out for Delivery";
            case DELIVERED:
                return "Delivered";
            default:
                return super.toString();
        }
    }
}
