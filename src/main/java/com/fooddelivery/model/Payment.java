package com.fooddelivery.model;

import com.fooddelivery.model.enums.PaymentMode;
import com.fooddelivery.model.enums.PaymentStatus;

import java.time.LocalDateTime;

public class Payment {

    private int paymentId;
    private double amount;
    private LocalDateTime paymentDate;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;
    private Order order;

    public Payment(int paymentId, double amount,
                   PaymentMode paymentMode, Order order) {

        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Amount cannot be negative"
            );
        }

        if (paymentMode == null || order == null) {
            throw new IllegalArgumentException(
                    "Payment mode and order are required"
            );
        }

        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.order = order;
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public void processPayment() {
        if (paymentStatus != PaymentStatus.PENDING) {
            return;
        }

        if (amount == order.calculateTotal()) {
            paymentStatus = PaymentStatus.SUCCESS;
            paymentDate = LocalDateTime.now();
        } else {
            paymentStatus = PaymentStatus.FAILED;
        }
    }

    public boolean isSuccessful() {
        return paymentStatus == PaymentStatus.SUCCESS;
    }

    public void refundPayment() {
        if (paymentStatus == PaymentStatus.SUCCESS) {
            paymentStatus = PaymentStatus.REFUNDED;
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

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public Order getOrder() {
        return order;
    }
}
