package com.fooddelivery.views;

import com.fooddelivery.model.FoodItem;
import com.fooddelivery.model.Order;
import com.fooddelivery.model.OrderItem;

import java.util.List;
import java.util.Scanner;


public class OrderView {

    private Scanner sc = new Scanner(System.in);

    public void showOrderMenu() {
        System.out.println("----- ORDER MENU -----");
        System.out.println("1. View Order");
        System.out.println("2. View All Orders");
        System.out.println("3. Confirm Order");
        System.out.println("4. Cancel Order");
        System.out.println("5. Back To Customer Menu");
    }

    public void displayOrder(Order order) {
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        System.out.println("----- ORDER DETAILS -----");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Date: " + order.getOrderDate());
        System.out.println("Status: " + order.getOrderStatus());

        for (OrderItem item : order.getOrderItems()) {
            System.out.println(item.getFoodItem().getFoodName() + " || Quantity: " + item.getQuantity() + " || Subtotal: Rs." + item.calculateSubtotal());
        }
        System.out.println("Total: Rs." + order.calculateTotal());
    }

    public void displayOrders(List<Order> orders) {
        System.out.println("----- MY ORDERS -----");

        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderId() + " || Total: Rs." + order.calculateTotal() + " || Status: " + order.getOrderStatus());
        }
    }

    public int getOrderId() {
        System.out.print("Enter Order ID: ");
        return sc.nextInt();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public int getMenuChoice(){
        return sc.nextInt();
    }
}


