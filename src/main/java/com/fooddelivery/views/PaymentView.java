package com.fooddelivery.views;

import com.fooddelivery.model.Payment;
import com.fooddelivery.model.enums.PaymentMode;
import com.fooddelivery.model.enums.PaymentStatus;

import java.util.Scanner;

public class PaymentView {

    private Scanner sc = new Scanner(System.in);

    public void showPaymentOptions() {
        System.out.println("\n----- PAYMENT OPTIONS -----");
        System.out.println("1. Cash");
        System.out.println("2. UPI");
        System.out.println("3. Card");
    }

    public PaymentMode getPaymentMode() {
        System.out.print("Choose payment mode: ");
        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1:
                return PaymentMode.CASH;
            case 2:
                return PaymentMode.UPI;
            case 3:
                return PaymentMode.CARD;
            default:
                System.out.println("Invalid payment mode.");
                return null;
        }
    }

    public void displayPaymentResult(PaymentStatus status) {
        System.out.println("\n----- PAYMENT RESULT -----");
        System.out.println("Payment Status: " + status);
    }

    public void displayPaymentDetails(Payment payment) {
        if (payment == null) {
            System.out.println("Payment details not found.");
            return;
        }

        System.out.println("\n----- PAYMENT DETAILS -----");
        System.out.println("Payment ID: " + payment.getPaymentId());
        System.out.println("Amount: Rs." + payment.getAmount());
        System.out.println("Payment Mode: " + payment.getPaymentMode());
        System.out.println("Payment Status: " + payment.getPaymentStatus());
        System.out.println("Payment Date: " + payment.getPaymentDate());
    }
}
