package com.fooddelivery.model;

public class OrderItem {

    private int orderItemId;
    private int quantity;
    private double itemPrice;
    private FoodItem foodItem;

    public OrderItem(int orderItemId, int quantity,
                     double itemPrice, FoodItem foodItem) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero"
            );
        }

        if (itemPrice < 0) {
            throw new IllegalArgumentException(
                    "Price cannot be negative"
            );
        }

        if (foodItem == null) {
            throw new IllegalArgumentException(
                    "Food item is required"
            );
        }

        this.orderItemId = orderItemId;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
        this.foodItem = foodItem;
    }

    public double calculateSubtotal() {
        return quantity * itemPrice;
    }

    public void increaseQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity += quantity;
        }
    }

    public void decreaseQuantity(int quantity) {
        if (quantity <= 0) {
            return;
        }

        if (quantity >= this.quantity) {
            this.quantity = 0;
        } else {
            this.quantity -= quantity;
        }
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }
}
