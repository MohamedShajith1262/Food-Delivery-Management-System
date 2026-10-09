package com.fooddelivery.views;


import com.fooddelivery.model.Address;
import com.fooddelivery.model.FoodItem;
import com.fooddelivery.model.Order;
import com.fooddelivery.model.Restaurant;

import java.util.List;
import java.util.Scanner;

public class CustomerView {

    private Scanner sc = new Scanner(System.in);

    public void showCustomerMenu() {
        System.out.println("\n----- CUSTOMER MENU -----");
        System.out.println("1. View Restaurants");
        System.out.println("2. Search Restaurant");
        System.out.println("3. View Food Items");
        System.out.println("4. Place Order");
        System.out.println("5. View My Orders");
        System.out.println("6. Update Address");
        System.out.println("7. Logout");
    }

    public void displayRestaurants(List<Restaurant> restaurants) {
        System.out.println("\n----- RESTAURANTS -----");

        if (restaurants.isEmpty()) {
            System.out.println("No restaurants available.");
            return;
        }

        for (Restaurant restaurant : restaurants) {
            System.out.println(
                    restaurant.getRestaurantId() + " - "
                            + restaurant.getName() + " - "
                            + restaurant.getLocation()
            );
        }
    }

    public void displayFoodItems(List<FoodItem> foodItems) {
        System.out.println("\n----- FOOD ITEMS -----");

        if (foodItems.isEmpty()) {
            System.out.println("No food items available.");
            return;
        }

        for (FoodItem item : foodItems) {
            System.out.println(
                    item.getFoodItemId() + " - "
                            + item.getFoodName() + " - Rs."
                            + item.getFoodPrice() + " - "
                            + (item.checkAvailability()
                            ? "Available" : "Unavailable")
            );
        }
    }

    public void displayOrders(List<Order> orders) {
        System.out.println("\n----- MY ORDERS -----");

        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        for (Order order : orders) {
            System.out.println(
                    "Order ID: " + order.getOrderId()
                            + " | Total: Rs." + order.calculateTotal()
                            + " | Status: " + order.getOrderStatus()
            );
        }
    }

    public String getSearchKeyword() {
        System.out.print("Enter restaurant name to search: ");
        return sc.nextLine();
    }

    public int getRestaurantId() {
        System.out.print("Enter restaurant ID: ");
        return Integer.parseInt(sc.nextLine());
    }

    public int getFoodItemId() {
        System.out.print("Enter food item ID: ");
        return Integer.parseInt(sc.nextLine());
    }

    public int getQuantity() {
        System.out.print("Enter quantity: ");
        return Integer.parseInt(sc.nextLine());
    }

    public Address getNewAddress() {
        System.out.println("\n----- UPDATE ADDRESS -----");

        System.out.print("Enter address: ");
        String line = sc.nextLine();

        System.out.print("Enter city: ");
        String city = sc.nextLine();

        System.out.print("Enter pincode: ");
        String pincode = sc.nextLine();

        return new Address(line, city, pincode);
    }
}


