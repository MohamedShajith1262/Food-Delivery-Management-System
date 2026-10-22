package com.fooddelivery.views;

import com.fooddelivery.model.FoodItem;
import com.fooddelivery.model.Order;
import com.fooddelivery.model.Restaurant;
import java.util.List;
import java.util.Scanner;

public class AdminView {

    private Scanner sc = new Scanner(System.in);

    public void showAdminMenu() {
        System.out.println("----- ADMIN MENU -----");
        System.out.println("1. Add Restaurant");
        System.out.println("2. Update Restaurant");
        System.out.println("3. Delete Restaurant");
        System.out.println("4. View Restaurants");
        System.out.println("5. View Restaurant Orders");
        System.out.println("6. Add Food Item");
        System.out.println("7. Update Food Item");
        System.out.println("8. Delete Food Item");
        System.out.println("9. view Restaurant Food Items");
        System.out.println("10. Logout");
    }

    public void displayRestaurants(List<Restaurant> restaurants) {
        System.out.println("----- DISPLAYING RESTAURANTS -----");
        if (restaurants.isEmpty()) {
            System.out.println("No restaurants found to display.");
            return;
        }
        for (Restaurant restaurant : restaurants) {
            System.out.println(restaurant.getRestaurantId() + " || " + restaurant.getName() + " || " + restaurant.getLocation() + " || " + restaurant.getPhoneNumber());
        }
    }

    public int getRestaurantId() {
        System.out.print("Enter restaurant ID: ");
        return Integer.parseInt(sc.nextLine());
    }

    public Restaurant getRestaurantDetails() {
        System.out.print("Enter restaurant name: ");
        String name = sc.nextLine();

        System.out.print("Enter location: ");
        String location = sc.nextLine();

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        return new Restaurant(name, location, phone);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public int getMenuChoice(){
        return Integer.parseInt(sc.nextLine());
    }

    public void displayRestaurantOrders(List<Order> orders){
        if (!orders.isEmpty()){
            for (Order order : orders){
                System.out.println("Order ID: " + order.getOrderId() +" || Order Items: "+ order.getOrderItems()
                        +" || Order Amount: "+ order.getTotalAmount() +" || Order Date: "+ order.getOrderDate()
                        +" || Order Status: "+ order.getOrderStatus() +" || Order Customer: "+ order.getCustomer());
            }
        }else {
            System.out.println("No Orders available yet...");
        }
    }

    public FoodItem getFoodItemDetails(){
        System.out.print("Enter Food Name: ");
        String foodName = sc.nextLine();

        System.out.print("Enter Food Price: ");
        double foodPrice = Double.parseDouble(sc.nextLine());

        System.out.print("Enter Food Category: ");
        String foodCategory = sc.nextLine();

        System.out.print("Is this food available? Give true/false: ");
        boolean foodAvailability = Boolean.parseBoolean(sc.nextLine());

        return new FoodItem(foodName, foodPrice, foodCategory, foodAvailability);
    }

    public void displayFoodItems(List<FoodItem> foodItems) {
        if (foodItems.isEmpty()) {
            System.out.println("No food items available.");
            return;
        }

        System.out.println("----- FOOD ITEMS -----");
        for (FoodItem foodItem : foodItems){
            System.out.println(foodItem.getFoodItemId() +" || "+ foodItem.getFoodName() +" || "+ foodItem.getFoodPrice() +" || "+ foodItem.getFoodCategory() +" || "+ foodItem.isAvailability());
        }
    }

    public int getFoodItemId() {
        System.out.print("Enter food item ID: ");
        return Integer.parseInt(sc.nextLine());
    }
}
