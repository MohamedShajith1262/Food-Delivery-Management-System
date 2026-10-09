package com.fooddelivery.model;

import com.fooddelivery.model.Order;
import com.fooddelivery.model.enums.Role;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private Address address;
    private List<Order> orders = new ArrayList<>();

    public Customer(int userId, String name, String phoneNumber,
                    String username, String password, Address address) {

        super(userId, name, phoneNumber, username, password,
                Role.CUSTOMER);

        this.address = address;
    }

    public void addOrder(Order order) {
        if (order != null && !orders.contains(order)) {
            orders.add(order);
        }
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public void updateAddress(Address address) {
        if (address != null) {
            this.address = address;
        }
    }

    public Address getAddress() {
        return address;
    }
}