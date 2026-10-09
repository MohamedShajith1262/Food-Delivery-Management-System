package com.fooddelivery.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private int restaurantId;
    private String name;
    private String location;
    private String phoneNumber;

    private List<FoodItem> foodItems = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public Restaurant(int restaurantId, String name,
                      String location, String phoneNumber) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
    }

    public void addFoodItem(FoodItem foodItem) {
        if (foodItem != null
                && findFoodItem(foodItem.getFoodItemId()) == null) {
            foodItems.add(foodItem);
        }
    }

    public void updateFoodItem(FoodItem foodItem) {
        if (foodItem == null) {
            return;
        }

        for (int i = 0; i < foodItems.size(); i++) {
            if (foodItems.get(i).getFoodItemId()
                    == foodItem.getFoodItemId()) {
                foodItems.set(i, foodItem);
                return;
            }
        }
    }

    public void deleteFoodItem(int foodItemId) {
        foodItems.removeIf(
                item -> item.getFoodItemId() == foodItemId
        );
    }

    public List<FoodItem> getFoodItems() {
        return new ArrayList<>(foodItems);
    }

    public FoodItem findFoodItem(int foodItemId) {
        for (FoodItem item : foodItems) {
            if (item.getFoodItemId() == foodItemId) {
                return item;
            }
        }
        return null;
    }

    public void addOrder(Order order) {
        if (order != null && !orders.contains(order)) {
            orders.add(order);
        }
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}