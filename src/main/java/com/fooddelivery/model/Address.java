package com.fooddelivery.model;

public class Address {

    private int addressId;
    private String doorNumber;
    private String streetName;
    private String city;
    private String pincode;

    public Address(int addressId, String doorNumber, String streetName, String city, String pincode) {
        this.addressId = addressId;
        this.doorNumber = doorNumber;
        this.streetName = streetName;
        this.city = city;
        this.pincode = pincode;
    }

    public int getAddressId() {
        return addressId;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public String getCity() {
        return city;
    }

    public String getPincode() {
        return pincode;
    }

    public String getStreetName(){
        return streetName;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}
