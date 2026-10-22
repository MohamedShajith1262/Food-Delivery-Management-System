package com.fooddelivery.model;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {

    public Admin(int userId, String name, String phoneNumber,
                 String username, String password) {

        super(userId, name, phoneNumber, username, password, "ADMIN");
    }

    public boolean addRestaurant(List<Restaurant> restaurants, Restaurant restaurant) {
        if (restaurant != null){
            restaurants.add(restaurant);
            return true;
        }
        return false;
    }

    public boolean updateRestaurant(List<Restaurant> restaurantList, Restaurant updatedRestaurant, int restaurantId) {
        if (updatedRestaurant == null) {
            return false;
        }

        Restaurant existingRestaurant = restaurantList.stream()
                .filter(restaurant -> restaurant.getRestaurantId() == restaurantId)
                .findFirst().orElse(null);

        if (existingRestaurant != null) {
            existingRestaurant.setName(updatedRestaurant.getName());
            existingRestaurant.setLocation(updatedRestaurant.getLocation());
            existingRestaurant.setPhoneNumber(updatedRestaurant.getPhoneNumber());
            return true;
        }
        return false;
    }

    public boolean deleteRestaurant(List<Restaurant> restaurants, int restaurantId) {
        if (!restaurants.isEmpty()) {
            restaurants.removeIf(restaurant ->
                    restaurant.getRestaurantId() == restaurantId);
            return true;
        }else {
            return false;
        }
    }

    public List<Order> getRestaurantOrders(List<Restaurant> restaurants, int restaurantId) {
        Restaurant selectedRestaurant = restaurants.stream()
                .filter(restaurant1-> restaurant1.getRestaurantId() == restaurantId)
                .findFirst().orElse(null);

        if (selectedRestaurant == null) {
            return new ArrayList<>();
        }

        return selectedRestaurant.getOrders();
    }

    public boolean addFoodItem(List<Restaurant> restaurants, int restaurantId, FoodItem foodItem) {
        if (foodItem == null) {
            return false;
        }
        Restaurant selectedRestaurant = restaurants.stream()
                .filter(restaurant -> restaurant.getRestaurantId() == restaurantId)
                .findAny().orElse(null);
        if (selectedRestaurant != null){
            selectedRestaurant.addFoodItem(foodItem);
            return true;
        }
        return false;
    }

    public boolean updateFoodItem(List<Restaurant> restaurants, int restaurantId, FoodItem updatedFoodItem, int foodItemId){
        if (updatedFoodItem == null){
            return false;
        }
        Restaurant findRestaurant = restaurants.stream()
                .filter(restaurant -> restaurant.getRestaurantId() == restaurantId)
                .findFirst().orElse(null);
        if (findRestaurant != null){
            findRestaurant.updateFoodItem(updatedFoodItem, foodItemId);
            return true;
        }
        return false;
    }

    public boolean deleteFoodItem(List<Restaurant> restaurants, int restaurantId, int foodItemId){
        Restaurant restaurant = restaurants.stream()
                .filter(r -> r.getRestaurantId() == restaurantId)
                .findFirst().orElse(null);
        if (restaurant != null){
            restaurant.deleteFoodItem(foodItemId);
            return true;
        }
        return false;
    }
}