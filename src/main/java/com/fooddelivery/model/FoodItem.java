package com.fooddelivery.model;

import com.fooddelivery.model.enums.FoodCategory;

public class FoodItem {

    private int foodItemId;
    private String foodName;
    private double foodPrice;
    private FoodCategory foodCategory;
    private boolean availability;

    public FoodItem(int foodItemId, String foodName,
                    double foodPrice, FoodCategory foodCategory,
                    boolean availability) {

        this.foodItemId = foodItemId;
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

    public FoodCategory getFoodCategory() {
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

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
