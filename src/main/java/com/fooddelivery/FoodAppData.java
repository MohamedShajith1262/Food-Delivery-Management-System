package com.fooddelivery;

import com.fooddelivery.model.Admin;
import com.fooddelivery.model.Payment;
import com.fooddelivery.model.Restaurant;
import com.fooddelivery.model.User;

import java.util.ArrayList;
import java.util.List;

public class FoodAppData {

    private final List<User> users = new ArrayList<>();
    private final List<Restaurant> restaurants = new ArrayList<>();
    private final List<Payment> payments = new ArrayList<>();

    public FoodAppData(){
        users.add(new Admin(1, "admin", "9807636712", "admin001", "adm0897"));
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public List<Payment> getPayments() {
        return payments;
    }
}
