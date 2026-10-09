package com.fooddelivery.views;


import com.fooddelivery.model.FoodItem;
import com.fooddelivery.model.Order;
import com.fooddelivery.model.OrderItem;
import com.fooddelivery.model.enums.FoodCategory;

import java.util.List;
import java.util.Scanner;


public class OrderView {

    private Scanner sc = new Scanner(System.in);

    public void showOrderMenu() {
        System.out.println("\n----- ORDER MENU -----");
        System.out.println("1. Add Food Item");
        System.out.println("2. Remove Food Item");
        System.out.println("3. View Order");
        System.out.println("4. Confirm Order");
        System.out.println("5. Cancel Order");
    }

    public void displayOrder(Order order) {
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        System.out.println("\n----- ORDER DETAILS -----");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Date: " + order.getOrderDate());
        System.out.println("Status: " + order.getOrderStatus());

        for (OrderItem item : order.getOrderItems()) {
            System.out.println(
                    item.getFoodItem().getFoodName()
                            + " | Quantity: " + item.getQuantity()
                            + " | Subtotal: Rs." + item.calculateSubtotal()
            );
        }

        System.out.println("Total: Rs." + order.calculateTotal());
    }

    public void displayOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        for (Order order : orders) {
            displayOrder(order);
        }
    }

    public int getOrderId() {
        System.out.print("Enter order ID: ");
        return Integer.parseInt(sc.nextLine());
    }

    public OrderItem getOrderItemDetails() {
        System.out.print("Enter order item ID: ");
        int orderItemId = Integer.parseInt(sc.nextLine());

        System.out.print("Enter food item ID: ");
        int foodItemId = Integer.parseInt(sc.nextLine());

        System.out.print("Enter food name: ");
        String foodName = sc.nextLine();

        System.out.print("Enter food price: ");
        double price = Double.parseDouble(sc.nextLine());

        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(sc.nextLine());

        FoodItem foodItem = new FoodItem(
                foodItemId,
                foodName,
                price,
                FoodCategory.MAIN_COURSE,
                true
        );

        return new OrderItem(
                orderItemId, quantity, price, foodItem
        );
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}


