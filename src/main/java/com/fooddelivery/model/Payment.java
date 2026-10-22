package com.fooddelivery.model;

import java.time.LocalDateTime;

public class Payment {

    private int paymentId;
    private double amount;
    private LocalDateTime paymentDate;
    private String paymentMode;
    private String paymentStatus;
    private Order order;

    public Payment(int paymentId, double amount, String paymentMode, Order order, LocalDateTime paymentDate) {

        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Amount cannot be negative"
            );
        }
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.order = order;
        this.paymentStatus = "PENDING";
        this.paymentDate = paymentDate;
    }

    public void processPayment() {
        if (!paymentStatus.equalsIgnoreCase("PENDING")) {
            return;
        }

        if (amount == order.calculateTotal()) {
            paymentStatus = "SUCCESS";
        } else {
            paymentStatus = "FAILED";
        }
    }

    public boolean isSuccessful() {
        return paymentStatus.equalsIgnoreCase("SUCCESS");
    }

    public void refundPayment() {
        if (isSuccessful()) {
            paymentStatus = "REFUNDED";
        }else {
            throw new IllegalStateException("Only Successful Payments can be Refunded...");
        }
    }

    public int getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public Order getOrder() {
        return order;
    }
}
