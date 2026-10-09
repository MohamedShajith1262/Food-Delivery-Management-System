package com.fooddelivery.views;

import com.fooddelivery.model.Restaurant;

import java.util.List;
import java.util.Scanner;

public class AdminView {

    private Scanner sc = new Scanner(System.in);

    public void showAdminMenu() {
        System.out.println("\n----- ADMIN MENU -----");
        System.out.println("1. Add Restaurant");
        System.out.println("2. View Restaurants");
        System.out.println("3. Update Restaurant");
        System.out.println("4. Delete Restaurant");
        System.out.println("5. View Restaurant Orders");
        System.out.println("6. Logout");
    }

    public void displayRestaurants(List<Restaurant> restaurants) {
        System.out.println("\n----- MANAGED RESTAURANTS -----");

        if (restaurants.isEmpty()) {
            System.out.println("No restaurants found.");
            return;
        }

        for (Restaurant restaurant : restaurants) {
            System.out.println(
                    restaurant.getRestaurantId() + " - "
                            + restaurant.getName() + " - "
                            + restaurant.getLocation() + " - "
                            + restaurant.getPhoneNumber()
            );
        }
    }

    public int getRestaurantId() {
        System.out.print("Enter restaurant ID: ");
        return Integer.parseInt(sc.nextLine());
    }

    public Restaurant getRestaurantDetails() {
        System.out.print("Enter restaurant ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Enter restaurant name: ");
        String name = sc.nextLine();

        System.out.print("Enter location: ");
        String location = sc.nextLine();

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        return new Restaurant(id, name, location, phone);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
