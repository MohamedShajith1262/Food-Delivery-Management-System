package com.fooddelivery.views;

import java.util.Scanner;


import com.fooddelivery.model.Address;
import com.fooddelivery.model.enums.Role;

public class AuthView {

    private Scanner sc = new Scanner(System.in);

    public void showLoginPrompt() {
        System.out.println("\n----- LOGIN -----");
    }

    public String getUsername() {
        System.out.print("Enter username: ");
        return sc.nextLine();
    }

    public String getPassword() {
        System.out.print("Enter password: ");
        return sc.nextLine();
    }

    public void displayLoginResult(boolean success) {
        if (success) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    public void showRegistrationForm() {
        System.out.println("\n----- REGISTRATION -----");
    }

    public void displayRegistrationResult(boolean success) {
        if (success) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed.");
        }
    }

    public Role getLoginType() {
        System.out.println("1. Customer");
        System.out.println("2. Admin");
        System.out.print("Choose account type: ");

        int choice = Integer.parseInt(sc.nextLine());

        if (choice == 1) {
            return Role.CUSTOMER;
        } else if (choice == 2) {
            return Role.ADMIN;
        }

        System.out.println("Invalid choice.");
        return null;
    }

    public String getName() {
        System.out.print("Enter your name: ");
        return sc.nextLine();
    }

    public String getPhoneNumber() {
        System.out.print("Enter phone number: ");
        return sc.nextLine();
    }

    public Address getAddress() {
        System.out.print("Enter address: ");
        String addressLine = sc.nextLine();

        System.out.print("Enter city: ");
        String city = sc.nextLine();

        System.out.print("Enter pincode: ");
        String pincode = sc.nextLine();

        return new Address(addressLine, city, pincode);
    }
}

