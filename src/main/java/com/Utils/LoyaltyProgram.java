package com.Utils;

import com.models.Customer;

import java.util.HashMap;
import java.util.Map;

public class LoyaltyProgram {
    private static Map<Customer, Integer> loyaltyPoints = new HashMap<>();

    public static void addPoints(Customer customer, int points) {
        loyaltyPoints.put(customer, loyaltyPoints.getOrDefault(customer, 0) + points);
    }

    public static int getPoints(Customer customer) {
        return loyaltyPoints.getOrDefault(customer, 0);
    }

    public static boolean redeemPoints(Customer customer, int pointsToRedeem) {
        int currentPoints = loyaltyPoints.getOrDefault(customer, 0);
        if (currentPoints >= pointsToRedeem) {
            loyaltyPoints.put(customer, currentPoints - pointsToRedeem);
            return true;
        }
        return false;
    }
}
