package com.fooddelivery.model;

import com.fooddelivery.model.enums.Role;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {

    private List<Restaurant> managedRestaurants = new ArrayList<>();

    public Admin(int userId, String name, String phoneNumber,
                 String username, String password) {

        super(userId, name, phoneNumber, username, password,
                Role.ADMIN);
    }

    public void addRestaurant(Restaurant restaurant) {
        if (restaurant != null
                && findRestaurant(restaurant.getRestaurantId()) == null) {

            managedRestaurants.add(restaurant);
        }
    }

    public void updateRestaurant(Restaurant restaurant) {
        if (restaurant == null) {
            return;
        }

        Restaurant existing =
                findRestaurant(restaurant.getRestaurantId());

        if (existing != null) {
            existing.setName(restaurant.getName());
            existing.setLocation(restaurant.getLocation());
            existing.setPhoneNumber(restaurant.getPhoneNumber());
        }
    }

    public void deleteRestaurant(int restaurantId) {
        managedRestaurants.removeIf(
                restaurant ->
                        restaurant.getRestaurantId() == restaurantId
        );
    }

    public List<Restaurant> getManagedRestaurants() {
        return new ArrayList<>(managedRestaurants);
    }

    public List<Order> getRestaurantOrders(int restaurantId) {
        Restaurant restaurant = findRestaurant(restaurantId);

        if (restaurant == null) {
            return new ArrayList<>();
        }

        return restaurant.getOrders();
    }

    private Restaurant findRestaurant(int restaurantId) {
        for (Restaurant restaurant : managedRestaurants) {
            if (restaurant.getRestaurantId() == restaurantId) {
                return restaurant;
            }
        }

        return null;
    }
}