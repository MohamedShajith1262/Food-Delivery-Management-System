package com.fooddelivery.controller;

import com.fooddelivery.FoodAppData;
import com.fooddelivery.model.Address;
import com.fooddelivery.model.Customer;
import com.fooddelivery.model.User;
import com.fooddelivery.views.AuthView;

public class AuthController {

    private AuthView authView;
    private FoodAppData appData;
    private User currentUser;
    private static int newUserId = 2;

    public AuthController(AuthView authView, FoodAppData appData) {
        this.authView = authView;
        this.appData = appData;
    }

    public boolean login(){
        authView.showLoginPrompt();
        String role = authView.getLoginType();
        String username = authView.getUsername();
        String password = authView.getPassword();

        for(User user : appData.getUsers()){
            if(user.getUsername().equals(username)
                    && user.getPassword().equals(password)
                    && user.getRole().equals(role)){
                currentUser = user;
                authView.displayLoginResult(true);
                return true;
            }
        }
        authView.displayLoginResult(false);
        return false;
    }

    public void register(){
        authView.showRegistrationForm();

        String name = authView.getName();
        String phone = authView.getPhoneNumber();
        String username = authView.getUsername();
        String password = authView.getPassword();
        String userRole = authView.getUserRole();
        Address address = authView.getAddress();

        if (userRole.equalsIgnoreCase("CUSTOMER")) {
            for (User user : appData.getUsers()) {
                if (user.getUsername().equals(username)) {
                    authView.displayRegistrationResult(false);
                    return;
                }
            }

            Customer customer = new Customer(newUserId++, name, phone, username, password, address);
            appData.getUsers().add(customer);
            authView.displayRegistrationResult(true);
        }else {
            throw new RuntimeException("Admin already exists");
        }
    }

    public void logout() {
        currentUser = null;
        System.out.println("Logged out successfully.");
    }

    public User getCurrentUser(){
        return currentUser;
    }
}
