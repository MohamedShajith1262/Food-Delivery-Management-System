package com.fooddelivery.model;

import com.fooddelivery.model.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderId;
    private LocalDateTime orderDate;
    private double totalAmount;
    private OrderStatus orderStatus;
    private List<OrderItem> orderItems = new ArrayList<>();
    private Customer customer;

    public Order(int orderId, Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException(
                    "Customer is required"
            );
        }

        this.orderId = orderId;
        this.customer = customer;
        this.orderDate = LocalDateTime.now();
        this.orderStatus = OrderStatus.PLACED;
        this.totalAmount = 0.0;
    }

    public void addOrderItem(OrderItem orderItem) {
        if (orderItem != null
                && orderItem.getQuantity() > 0
                && !orderItems.contains(orderItem)) {

            orderItems.add(orderItem);
            calculateTotal();
        }
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
        calculateTotal();
    }

    public List<OrderItem> getOrderItems() {
        return new ArrayList<>(orderItems);
    }

    public double calculateTotal() {
        totalAmount = 0.0;

        for (OrderItem item : orderItems) {
            totalAmount += item.calculateSubtotal();
        }

        return totalAmount;
    }

    public void updateStatus(OrderStatus status) {
        if (status != null) {
            this.orderStatus = status;
        }
    }

    public void cancelOrder() {
        if (orderStatus == OrderStatus.PLACED
                || orderStatus == OrderStatus.CONFIRMED) {
            orderStatus = OrderStatus.CANCELLED;
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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Customer getCustomer() {
        return customer;
    }
}