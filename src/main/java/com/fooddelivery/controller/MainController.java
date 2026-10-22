package com.fooddelivery.controller;

import com.fooddelivery.model.Customer;
import com.fooddelivery.model.Restaurant;
import com.fooddelivery.model.User;
import com.fooddelivery.views.MainView;

public class MainController {

    private MainView mainView;
    private AuthController authController;
    private CustomerController customerController;
    private AdminController adminController;

    public MainController(MainView mainView, AuthController authController, CustomerController customerController, AdminController adminController) {
        this.mainView = mainView;
        this.authController = authController;
        this.customerController = customerController;
        this.adminController = adminController;
    }

    public void start(){
        mainView.showWelcomeMessage();
    }

    public void handleMainMenu() {
        boolean executingMenu = true;
        while (executingMenu) {
            mainView.showMainMenu();
            int menuChoice = mainView.getMenuChoice();
            switch (menuChoice){
                case 1:
                    authController.register();
                    break;
                case 2:
                    boolean loginUser = authController.login();
                    if (loginUser){
                        User loggedInUser = authController.getCurrentUser();
                        if (loggedInUser.getRole().equalsIgnoreCase( "CUSTOMER") && loggedInUser instanceof Customer){
                            Customer loggedInCustomer = (Customer) loggedInUser;
                            customerController.setCustomer(loggedInCustomer);
                            customerController.showMenu();
                        } else if (loggedInUser.getRole().equalsIgnoreCase( "ADMIN")) {
                            adminController.showMenu();
                        }
                    }
                    break;
                case 3:
                    System.out.println("Thank You for using Food Delivery System!");
                    executingMenu = false;
                    break;
                default:
                    System.out.println("Invalid Choice! Choose Correct Option...");
            }
        }
    }
}
