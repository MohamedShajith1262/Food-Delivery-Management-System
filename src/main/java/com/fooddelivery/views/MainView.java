package com.fooddelivery.views;

import java.util.Scanner;

public class MainView {

    private Scanner sc = new Scanner(System.in);

    public void showWelcomeMessage() {
        System.out.println("================================");
        System.out.println("   FOOD DELIVERY SYSTEM");
        System.out.println("================================");
        System.out.println("Welcome! Order your favourite food.");
    }

    public void showMainMenu() {
        System.out.println("\n----- MAIN MENU -----");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
    }

    public int getMenuChoice() {
        System.out.print("Enter your choice: ");
        return Integer.parseInt(sc.nextLine());
    }
}

