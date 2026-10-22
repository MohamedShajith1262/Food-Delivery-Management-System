package com.fooddelivery.controller;

import com.fooddelivery.FoodAppData;
import com.fooddelivery.model.Customer;
import com.fooddelivery.model.FoodItem;
import com.fooddelivery.model.Order;
import com.fooddelivery.model.Payment;
import com.fooddelivery.views.PaymentView;

import java.time.LocalDateTime;

public class PaymentController {

    private PaymentView paymentView;
    private Customer customer;
    private FoodAppData appData;
    private static int nextPaymentId = 1;

    public PaymentController(PaymentView paymentView, FoodAppData appData) {
        this.paymentView = paymentView;
        this.appData = appData;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void makePayment(Order order){
        if (order == null){
            paymentView.displayPaymentResult("Order not found...");
            return;
        }

        if (customer == null || order.getCustomer() != customer) {
            paymentView.displayPaymentResult("This order does not belong to the current customer.");
            return;
        }

        if (!"PENDING".equals(order.getOrderStatus())) {
            paymentView.displayPaymentResult("This order is not eligible for payment.");
            return;
        }

        Payment existingPayment = findPaymentByOrderId(order.getOrderId());
        if (existingPayment != null && existingPayment.isSuccessful()){
            paymentView.displayPaymentResult("This Order has already been paid...");
            return;
        }

        paymentView.showPaymentOptions();
        String paymentMode = paymentView.getPaymentMode();
        Payment payment = processPayment(order, paymentMode);
        appData.getPayments().add(payment);
        displayPaymentResult(payment);
    }

    public Payment findPaymentByOrderId(int orderId) {
        Payment selectedPayment = appData.getPayments().stream().
                filter(payment -> payment.getOrder() != null && payment.getOrder().getOrderId() == orderId).
                findAny().orElse(null);
        return selectedPayment;
    }

    private Payment processPayment(Order order, String paymentMode){
        double paymentAmount = order.calculateTotal();
        Payment payment = new Payment(nextPaymentId++, paymentAmount, paymentMode, order,LocalDateTime.now());
        payment.processPayment();
        return payment;
    }

    private void displayPaymentResult(Payment payment){
        if (payment.isSuccessful()){
            payment.getOrder().updateStatus("CONFIRMED");
            paymentView.displayPaymentResult("PAYMENT SUCCESS - ORDER CONFIRMED!");
            paymentView.displayPaymentDetails(payment);
        }else {
            paymentView.displayPaymentResult("PAYMENT FAILED - ORDER IS NOT CONFIRMED!");
        }
    }

    public boolean refundPaymentForOrder(Order order) {
        if (order == null || customer == null || order.getCustomer() != customer) {
            return false;
        }

        Payment payment = findPaymentByOrderId(order.getOrderId());

        if (payment == null || !payment.isSuccessful()) {
            return false;
        }

        try {
            payment.refundPayment();
            return "REFUNDED".equalsIgnoreCase(payment.getPaymentStatus());
        } catch (IllegalStateException exception) {
            return false;
        }
    }
}
