package com.fooddelivery.model;

public class FoodItem {

    private int foodItemId;
    private String foodName;
    private double foodPrice;
    private String foodCategory;
    private boolean availability;

    public FoodItem(int foodItemId, String foodName,
                    double foodPrice, String foodCategory,
                    boolean availability) {

        this.foodItemId = foodItemId;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodCategory = foodCategory;
        this.availability = availability;
    }

    public FoodItem(String foodName, double foodPrice, String foodCategory, boolean availability) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodCategory = foodCategory;
        this.availability = availability;
    }

    public boolean checkAvailability() {
        return availability;
    }

    public int getFoodItemId() {
        return foodItemId;
    }

    public String getFoodName() {
        return foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodPrice(double foodPrice) {
        if (foodPrice >= 0) {
            this.foodPrice = foodPrice;
        }
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
