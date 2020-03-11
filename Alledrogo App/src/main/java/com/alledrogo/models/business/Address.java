package com.alledrogo.models.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AddressBook")
public class Address {
    @Id
    @Column(name = "addressID")
    private int addressID;

    @Column(name = "userID")
    private int userID;

    @Column(name = "addressFirstLane")
    private String addressFirstLane;

    @Column(name = "addressSecondLane")
    private String addressSecondLane;

    @Column(name = "zipCode")
    private String zipCode;

    @Column(name = "city")
    private String city;


    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAddressFirstLane() {
        return addressFirstLane;
    }

    public void setAddressFirstLane(String addressFirstLane) {
        this.addressFirstLane = addressFirstLane;
    }

    public String getAddressSecondLane() {
        return addressSecondLane;
    }

    public void setAddressSecondLane(String addressSecondLane) {
        this.addressSecondLane = addressSecondLane;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
