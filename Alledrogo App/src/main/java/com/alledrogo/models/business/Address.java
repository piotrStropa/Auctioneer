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

    public static final class AddressBuilder {
        private int addressID;
        private int userID;
        private String addressFirstLane;
        private String addressSecondLane;
        private String zipCode;
        private String city;

        private AddressBuilder() {
        }

        public static AddressBuilder anAddress() {
            return new AddressBuilder();
        }

        public AddressBuilder withAddressID(int addressID) {
            this.addressID = addressID;
            return this;
        }

        public AddressBuilder withUserID(int userID) {
            this.userID = userID;
            return this;
        }

        public AddressBuilder withAddressFirstLane(String addressFirstLane) {
            this.addressFirstLane = addressFirstLane;
            return this;
        }

        public AddressBuilder withAddressSecondLane(String addressSecondLane) {
            this.addressSecondLane = addressSecondLane;
            return this;
        }

        public AddressBuilder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public AddressBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public Address build() {
            Address address = new Address();
            address.setAddressID(addressID);
            address.setUserID(userID);
            address.setAddressFirstLane(addressFirstLane);
            address.setAddressSecondLane(addressSecondLane);
            address.setZipCode(zipCode);
            address.setCity(city);
            return address;
        }
    }
}
