package com.fooddelivery.model;

public class User {

    private int userId;
    private String name;
    private String phoneNumber;
    private String username;
    private String password;
    private String role;

    public User(int userId, String name, String phoneNumber, String username, String password, String role) {
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}