package com.models;

import java.io.Serializable;

public class Customer implements Serializable {
    private String name;
    private String address;
    private String crustPreference;
    private String saucePreference;
    private String[] toppingsPreference;

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    // Setters for preferences
    public void setCrustPreference(String crust) {
        this.crustPreference = crust;
    }

    public void setSaucePreference(String sauce) {
        this.saucePreference = sauce;
    }

    public void setToppingsPreference(String[] toppings) {
        this.toppingsPreference = toppings;
    }

    public void savePreferences(String filePath) {
    }
}
