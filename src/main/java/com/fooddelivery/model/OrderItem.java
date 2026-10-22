package com.fooddelivery.model;

public class OrderItem {

    private int orderItemId;
    private int quantity;
    private double itemPrice;
    private FoodItem foodItem;

    public OrderItem(int orderItemId, int quantity,
                     double itemPrice, FoodItem foodItem) {

        if(foodItem == null || quantity <= 0 || itemPrice <= 0){
            throw new IllegalArgumentException("Please Check your Order Items,every item must have a valid food selection and positive values...");
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
