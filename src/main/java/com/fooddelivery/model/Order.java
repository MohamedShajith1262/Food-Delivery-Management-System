package com.fooddelivery.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderId;
    private LocalDateTime orderDate;
    private double totalAmount;
    private String orderStatus;
    private List<OrderItem> orderItems = new ArrayList<>();
    private Customer customer;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderDate = LocalDateTime.now();
        this.orderStatus = "PENDING";
        this.totalAmount = 0.0;
    }

    public void addOrderItem(OrderItem orderItem) {
        if (orderItem != null && orderItem.getQuantity() > 0 && !orderItems.contains(orderItem)) {
            orderItems.add(orderItem);
        }
    }

  /*  public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
    }
*/
    public List<OrderItem> getOrderItems() {
        return new ArrayList<>(orderItems);
    }

    public double calculateTotal() {
        totalAmount = 0;
        for (OrderItem item : orderItems) {
            totalAmount += item.calculateSubtotal();
        }
        return totalAmount;
    }

    public void updateStatus(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null.");
        }

        if ("CONFIRMED".equals(status) && !"PENDING".equals(orderStatus)) {
            throw new IllegalStateException("Only pending orders can be confirmed...");
        }
        this.orderStatus = status;
    }

    public void cancelOrder() {
        if (orderStatus.equalsIgnoreCase("PENDING")
                || orderStatus.equalsIgnoreCase("CONFIRMED")) {
            orderStatus = "CANCELLED";
        } else {
            throw new IllegalStateException("Only pending or confirmed orders can be cancelled...");
        }
    }

    public int getOrderId() {
        return orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public Customer getCustomer() {
        return customer;
    }
}