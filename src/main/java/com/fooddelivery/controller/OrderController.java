package com.fooddelivery.controller;

import com.fooddelivery.model.Customer;
import com.fooddelivery.model.Order;
import com.fooddelivery.model.Payment;
import com.fooddelivery.views.OrderView;

import java.util.List;

public class OrderController {

    private OrderView orderView;
    private Customer customer;
    private PaymentController paymentController;

    public OrderController(OrderView orderView, PaymentController paymentController) {
        this.orderView = orderView;
        this.paymentController = paymentController;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public void showOrderMenu() {
        boolean runningOrderMenu = true;
        while (runningOrderMenu) {
            orderView.showOrderMenu();
            int orderMenuChoice = orderView.getMenuChoice();
            switch (orderMenuChoice){
                case 1:
                    viewOrder();
                    break;
                case 2:
                    viewAllOrders();
                    break;
                case 3:
                    confirmOrder();
                    break;
                case 4:
                    cancelOrder();
                    break;
                case 5:
                    runningOrderMenu = false;
                    break;
                default:
                    System.out.println("Invalid Menu Choice...");
            }
        }
    }

    public void viewOrder(){
        if (customer == null){
            return;
        }
        int orderId = orderView.getOrderId();
        List<Order> orderList = customer.getOrders();
        Order selectOrder = orderList.stream()
                .filter(order -> order.getOrderId() == orderId)
                .findAny().orElse(null);
        if (selectOrder == null){
            orderView.displayMessage("Order not found...");
        }
        orderView.displayOrder(selectOrder);
    }

    public void viewAllOrders(){
        if (customer == null){
            orderView.displayMessage("No Customer is Logged-In...");
        }
        List<Order> customerOrders = customer.getOrders();
        orderView.displayOrders(customerOrders);
    }

    public void confirmOrder(){
        int orderId = orderView.getOrderId();
        Order confOrder = customer.getOrders().stream()
                .filter(order -> order.getOrderId() == orderId)
                .findAny().orElseThrow(()-> new RuntimeException("Unable to confirm this order..."));

        if (confOrder == null){
            orderView.displayMessage("Order not found...");
            return;
        }

        if ("CONFIRMED".equals(confOrder.getOrderStatus())) {
            orderView.displayMessage("Order is already confirmed.");
            return;
        }

        if (!"PENDING".equals(confOrder.getOrderStatus())) {
            orderView.displayMessage("This order cannot be confirmed.");
            return;
        }

        Payment payment = paymentController.findPaymentByOrderId(orderId);

        if (payment == null || !payment.isSuccessful()) {
            orderView.displayMessage("Successful payment is required to confirm this order.");
            return;
        }

        confOrder.updateStatus("CONFIRMED");
        orderView.displayMessage("Order confirmed successfully.");
    }

    public void cancelOrder(){
        int orderId = orderView.getOrderId();
        Order canclOrder = customer.getOrders().stream()
                .filter(order -> order.getOrderId() == orderId)
                .findAny().orElseThrow(()-> new RuntimeException("Unable to cancel this order..."));

        if (!"PENDING".equals(canclOrder.getOrderStatus()) && !"CONFIRMED".equals(canclOrder.getOrderStatus())) {
            orderView.displayMessage("This order cannot be cancelled...");
            return;
        }

        if ("CONFIRMED".equals(canclOrder.getOrderStatus())) {
            boolean refunded = paymentController.refundPaymentForOrder(canclOrder);

            if (!refunded) {
                orderView.displayMessage("Refund failed. Order was not cancelled.");
                return;
            }
        }
        canclOrder.cancelOrder();
        orderView.displayMessage("Order cancelled successfully.");
    }
}
