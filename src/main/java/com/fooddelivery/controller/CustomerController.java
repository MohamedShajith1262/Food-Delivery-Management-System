package com.fooddelivery.controller;

import com.fooddelivery.FoodAppData;
import com.fooddelivery.model.*;
import com.fooddelivery.views.CustomerView;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {

    private CustomerView customerView;
    private OrderController orderController;
    private Customer customer;
    private FoodAppData appData;
    private AuthController authController;
    private PaymentController paymentController;
    private static int orderId = 1;
    private static int orderItemId = 1;

    public CustomerController(CustomerView customerView, OrderController orderController, FoodAppData appData, AuthController authController, PaymentController paymentController) {
        this.customerView = customerView;
        this.orderController = orderController;
        this.appData = appData;
        this.authController = authController;
        this.paymentController = paymentController;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        orderController.setCustomer(customer);
        paymentController.setCustomer(customer);
    }

    public void showMenu(){
        boolean runningCustomerMenu = true;
        while (runningCustomerMenu){
            customerView.showCustomerMenu();
            int customerChoice = customerView.getMenuChoice();
            switch (customerChoice){
                    case 1:
                        viewRestaurants();
                        break;

                    case 2:
                        searchFood();
                        break;

                    case 3:
                        viewFoodItems();
                        break;

                    case 4:
                        placeOrder();
                        break;

                    case 5:
                        viewOrders();
                        break;

                    case 6:
                        updateAddress();
                        break;

                    case 7:
                        authController.logout();
                        runningCustomerMenu = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public void viewRestaurants(){
        if(appData.getRestaurants().isEmpty()){
            customerView.displayMessage("No Restaurants Available");
        }
        customerView.displayRestaurants(appData.getRestaurants());
    }

    public void searchFood(){
        this.viewRestaurants();
        String searchingFood = customerView.getSearchingFood();
        List<FoodItem> searchedFoodItems = new ArrayList<>();

        for (Restaurant restaurant : appData.getRestaurants()) {
            for (FoodItem foodItem : restaurant.getFoodItems()) {
                if (foodItem.getFoodName().contains(searchingFood)) {
                    searchedFoodItems.add(foodItem);
                }
            }
        }

        if (searchedFoodItems.isEmpty()) {
            customerView.displayMessage("No matching food items found.");
        } else {
            customerView.displayFoodItems(searchedFoodItems);
        }
    }

    public void placeOrder(){
        this.viewRestaurants();
        int restaurantId = customerView.getRestaurantId();
        Restaurant selectedRestaurant = appData.getRestaurants().stream()
                .filter(restaurant -> restaurant.getRestaurantId() == restaurantId)
                .findFirst().orElse(null);

        if (selectedRestaurant == null){
            customerView.displayMessage("No Restaurant found...");
            return;
        }

        customerView.displayFoodItems(selectedRestaurant.getFoodItems());

        int foodItemId = customerView.getFoodItemId();

        FoodItem selectedFood = selectedRestaurant.getFoodItems().stream()
                .filter(foodItem -> foodItem.getFoodItemId() == foodItemId)
                .findFirst().orElseThrow(()-> new RuntimeException("Food item not found."));

        int quantity = customerView.getQuantity();

        OrderItem orderItem = new OrderItem(orderItemId++, quantity, selectedFood.getFoodPrice(), selectedFood);

        Order order = new Order(orderId++, customer);
        order.addOrderItem(orderItem);
        customer.addOrder(order);
        paymentController.makePayment(order);
        
    }

    public void viewFoodItems() {
        this.viewRestaurants();
        int restaurantId = customerView.getRestaurantId();
        Restaurant selectedRestaurant = appData.getRestaurants().stream()
                .filter(restaurant -> restaurant.getRestaurantId() == restaurantId)
                .findFirst().orElseThrow(()-> new RuntimeException("Restaurant not found."));

        if (selectedRestaurant.getFoodItems().isEmpty()) {
            customerView.displayMessage("No food items in this restaurant.");
            return;
        }
        customerView.displayFoodItems(selectedRestaurant.getFoodItems());
    }

    public void viewOrders() {
        if (customer.getOrders().isEmpty()) {
            customerView.displayMessage("You have no orders yet.");
            return;
        }
        orderController.showOrderMenu();
    }

    public void updateAddress() {
        Address newAddress = customerView.getNewAddress();
        customer.updateAddress(newAddress);
        customerView.displayMessage("Address updated successfully.");
    }

  /*  public Restaurant findRestaurant(int restaurantId) {
        for (Restaurant restaurant : appData.getRestaurants()) {
            if (restaurant.getRestaurantId() == restaurantId) {
                return restaurant;
            }
        }
        return null;
    }

   public FoodItem findFoodItem(Restaurant restaurant, int foodItemId) {
        for (FoodItem foodItem : restaurant.getFoodItems()) {
            if (foodItem.getFoodItemId() == foodItemId) {
                return foodItem;
            }
        }
        return null;
    } */
}



