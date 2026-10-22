package com.fooddelivery.controller;

import com.fooddelivery.FoodAppData;
import com.fooddelivery.model.Admin;
import com.fooddelivery.model.FoodItem;
import com.fooddelivery.model.Order;
import com.fooddelivery.model.Restaurant;
import com.fooddelivery.views.AdminView;

import java.util.List;

public class AdminController {

    private AdminView adminView;
    private Admin admin;
    private FoodAppData appData;
    private AuthController authController;
    private static int restaurantId = 1;
    private static int foodItemId = 1;


    public AdminController(AdminView adminView, Admin admin, FoodAppData appData, AuthController authController) {
        this.adminView = adminView;
        this.admin = admin;
        this.appData = appData;
        this.authController = authController;
    }

    public void showMenu() {
        boolean executeAdminMenu = true;
        while (executeAdminMenu) {
            adminView.showAdminMenu();
            int adminChoice = adminView.getMenuChoice();
            switch (adminChoice) {
                case 1:
                    addRestaurant();
                    break;
                case 2:
                    updateRestaurant();
                    break;
                case 3:
                    deleteRestaurant();
                    break;
                case 4:
                    viewRestaurants();
                    break;
                case 5:
                    viewRestaurantOrders();
                    break;
                case 6:
                    addFoodItem();
                    break;
                case 7:
                    updateFoodItem();
                    break;
                case 8:
                    deleteFoodItem();
                    break;
                case 9:
                    viewRestaurantFoodItems();
                    break;
                case 10:
                    authController.logout();
                    executeAdminMenu = false;
                    break;
                default:
                    adminView.displayMessage("Invalid Menu Choice...");
            }
        }
    }

    public void addRestaurant(){
        Restaurant restaurantDetails = adminView.getRestaurantDetails();
        if (restaurantDetails == null) {
            adminView.displayMessage("Restaurant details are invalid.");
            return;
        }
        Restaurant restaurant = new Restaurant(restaurantId++, restaurantDetails.getName(), restaurantDetails.getLocation(), restaurantDetails.getPhoneNumber());
        boolean isAdded = admin.addRestaurant(appData.getRestaurants(), restaurant);
        if (isAdded) {
            adminView.displayMessage(restaurant.getName() + " Restaurant Added Successfully...");
        }else {
            adminView.displayMessage(restaurant.getName() + " Restaurant Failed to add...");
        }
    }

    public void updateRestaurant(){
        int updateRestaurantId = adminView.getRestaurantId();
        Restaurant updatedRestaurant = adminView.getRestaurantDetails();
        boolean isUpdated = admin.updateRestaurant(appData.getRestaurants(), updatedRestaurant, updateRestaurantId);
        if (isUpdated){
            adminView.displayMessage(updatedRestaurant.getName() + " Restaurant Updated Successfully...");
        }else {
            adminView.displayMessage(updatedRestaurant.getName() + " Restaurant Failed to update...");
        }
    }

    public void deleteRestaurant(){
        int deleteRestaurantId = adminView.getRestaurantId();
        boolean isDeleted = admin.deleteRestaurant(appData.getRestaurants(), deleteRestaurantId);
        if (isDeleted){
            adminView.displayMessage("Restaurant ID: " + deleteRestaurantId + " Successfully Deleted...");
        }else {
            adminView.displayMessage("Restaurant not found...");
        }
    }

    public void viewRestaurants(){
        List<Restaurant> restaurants = appData.getRestaurants();
        if (!restaurants.isEmpty()){
            adminView.displayRestaurants(restaurants);
        }else {
            System.out.println("No Restaurant found!...");
        }
    }

    public void viewRestaurantOrders(){
        int viewRestaurantId = adminView.getRestaurantId();
        List<Order> orders = admin.getRestaurantOrders(appData.getRestaurants(), viewRestaurantId);
        adminView.displayRestaurantOrders(orders);
    }

    public void addFoodItem(){
        int restaurantId = adminView.getRestaurantId();
        FoodItem storedFood = adminView.getFoodItemDetails();
        FoodItem foodItem = new FoodItem(foodItemId++, storedFood.getFoodName(), storedFood.getFoodPrice(), storedFood.getFoodCategory(), storedFood.isAvailability());
        boolean isAddedFoodItem = admin.addFoodItem(appData.getRestaurants(), restaurantId,foodItem);
        if (isAddedFoodItem){
            adminView.displayMessage("Food Item successfully added to the Restaurant Id: "+ restaurantId);
        }else {
            adminView.displayMessage("Restaurant not found - so failed to add food item...");
        }
    }

    public void updateFoodItem(){
        int restaurantId = adminView.getRestaurantId();
        Restaurant restaurant = appData.getRestaurants().stream()
                .filter(r -> r.getRestaurantId() == restaurantId)
                .findFirst().orElse(null);
        if (restaurant == null){
            adminView.displayMessage("Restaurant not found for this Id: "+restaurantId);
            return;
        }
        adminView.displayFoodItems(restaurant.getFoodItems());
        int foodItemId = adminView.getFoodItemId();
        FoodItem updatedFoodItem = adminView.getFoodItemDetails();
        boolean isUpdatedFood = admin.updateFoodItem(appData.getRestaurants(),restaurantId, updatedFoodItem, foodItemId);
        if (isUpdatedFood){
            adminView.displayMessage("Food Item successfully updated to this restaurant Id: "+ restaurantId);
        }else {
            adminView.displayMessage("Failed to update food item...");
        }
    }

    public void deleteFoodItem(){
        int restaurantId = adminView.getRestaurantId();
        Restaurant restaurant = appData.getRestaurants().stream()
                .filter(r -> r.getRestaurantId() == restaurantId)
                .findFirst().orElse(null);
        if (restaurant == null){
            adminView.displayMessage("Restaurant not found for this Id: "+restaurantId);
            return;
        }
        adminView.displayFoodItems(restaurant.getFoodItems());
        if (restaurant.getFoodItems().isEmpty()){
            return;
        }
        int foodItemId = adminView.getFoodItemId();
        boolean isDeletedFood = admin.deleteFoodItem(appData.getRestaurants(),restaurantId, foodItemId);
        if (isDeletedFood){
            adminView.displayMessage("Food Item successfully deleted for this restaurant Id: "+ restaurantId);
        }else {
            adminView.displayMessage("Failed to delete food item...");
        }
    }

    public void viewRestaurantFoodItems() {
        int restaurantId = adminView.getRestaurantId();
        Restaurant selectedRestaurant = appData.getRestaurants().stream()
                .filter(restaurant -> restaurant.getRestaurantId() == restaurantId)
                .findFirst().orElse(null);

        if (selectedRestaurant == null) {
            adminView.displayMessage("Restaurant not found.");
            return;
        }

        adminView.displayFoodItems(selectedRestaurant.getFoodItems());
    }
}