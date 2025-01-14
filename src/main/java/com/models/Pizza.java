package com.models;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Pizza {
    private String crust;
    private String sauce;
    private String[] toppings;
    private double price;

    public Pizza(String crust, String sauce, String[] toppings) {
        this.crust = crust;
        this.sauce = sauce;
        this.toppings = toppings != null ? toppings : new String[0];
        this.price = calculatePrice();
    }

    public double calculatePrice() {
        double basePrice = 8.0;
        basePrice += toppings.length * 1.5;
        return basePrice;
    }

    @Override
    public String toString() {
        String toppingsList = Arrays.stream(toppings)
                .filter(topping -> topping != null && !topping.isEmpty())
                .collect(Collectors.joining(", "));

        return "Crust: " + crust + ", Sauce: " + sauce + ", Toppings: " + toppingsList;
    }

    public double getPrice() {
        return price;
    }
}
