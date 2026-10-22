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
        System.out.println("----- CUSTOMER MENU -----");
        System.out.println("1. View Restaurants");
        System.out.println("2. Search Restaurant");
        System.out.println("3. View Food Items");
        System.out.println("4. Place Order");
        System.out.println("5. View My Orders");
        System.out.println("6. Update Address");
        System.out.println("7. Logout");
    }

    public void displayRestaurants(List<Restaurant> restaurants) {
        System.out.println("----- RESTAURANTS -----");

        for (Restaurant restaurant : restaurants) {
            System.out.println(restaurant.getRestaurantId() + " - " + restaurant.getName() + " - " + restaurant.getLocation());
        }
    }

    public void displayFoodItems(List<FoodItem> foodItems) {
        System.out.println("----- FOOD ITEMS -----");
        for (FoodItem item : foodItems) {
            System.out.println(item.getFoodItemId() + " || " + item.getFoodName() + " || Rs." + item.getFoodPrice() + " || " + (item.checkAvailability() ? "Available" : "Unavailable"));
        }
    }

   /* public void displayOrders(List<Order> orders) {
        System.out.println("----- MY ORDERS -----");

        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderId() + " || Total: Rs." + order.calculateTotal() + " || Status: " + order.getOrderStatus());
        }
    } */

    public String getSearchingFood() {
        System.out.print("Enter food name to search: ");
        return sc.nextLine();
    }

    public int getRestaurantId() {
        System.out.print("Enter restaurant ID: ");
        return sc.nextInt();
    }

    public int getFoodItemId() {
        System.out.print("Enter food item ID: ");
        return sc.nextInt();
    }

    public int getQuantity() {
        System.out.print("Enter quantity: ");
        return sc.nextInt();
    }

    public Address getNewAddress() {
        System.out.println("----- UPDATE ADDRESS -----");

        System.out.println("Enter Address Id: ");
        int addressId = sc.nextInt();

        System.out.print("Enter Door Number: ");
        String doorNumber = sc.nextLine();

        System.out.println("Enter Street Name: ");
        String streetName = sc.nextLine();

        System.out.print("Enter City: ");
        String city = sc.nextLine();

        System.out.print("Enter PinCode: ");
        String pinCode = sc.nextLine();

        return new Address(addressId, doorNumber, streetName, city, pinCode);
    }

    public int getMenuChoice(){
        System.out.println("Enter your Menu Choice:");
        return sc.nextInt();
    }

    public void displayMessage(String message){
        System.out.println(message);
    }
}


