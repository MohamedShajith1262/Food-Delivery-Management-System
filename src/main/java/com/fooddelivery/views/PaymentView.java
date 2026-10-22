package com.fooddelivery.views;

import com.fooddelivery.model.Payment;
import java.util.Scanner;

public class PaymentView {

    private Scanner sc = new Scanner(System.in);

    public void showPaymentOptions() {
        System.out.println("----- PAYMENT OPTIONS -----");
        System.out.println("1. Cash");
        System.out.println("2. UPI");
        System.out.println("3. Card");
    }

    public String getPaymentMode() {
        System.out.print("Choose Payment Mode: ");
        int paymentChoice = sc.nextInt();

        switch (paymentChoice) {
            case 1:
                return "CASH";
            case 2:
                return "UPI";
            case 3:
                return "CARD";
            default:
                System.out.println("Invalid payment mode!.. Please choose the correct option...");
                return null;
        }
    }

    public void displayPaymentResult(String status) {
        System.out.println("----- PAYMENT RESULT -----");
        System.out.println("Payment Status: " + status);
        System.out.println("---------------------------");
    }

    public void displayPaymentDetails(Payment payment) {
        if (payment == null) {
            System.out.println("Payment details not found.");
            return;
        }

        System.out.println("----- PAYMENT DETAILS -----");
        System.out.println("Payment ID: " + payment.getPaymentId());
        System.out.println("Amount: Rs." + payment.getAmount());
        System.out.println("Payment Mode: " + payment.getPaymentMode());
        System.out.println("Payment Status: " + payment.getPaymentStatus());
        System.out.println("Payment Date: " + payment.getPaymentDate());
    }
}
