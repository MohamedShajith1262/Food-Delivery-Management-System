package com.fooddelivery.model;

public class Address {

    private String addressLine;
    private String city;
    private String pincode;

    public Address(String addressLine, String city, String pincode) {
        this.addressLine = addressLine;
        this.city = city;
        this.pincode = pincode;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public String getCity() {
        return city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
