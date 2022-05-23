package com.revature.TeamCP2.dtos;

public class UpdateAddressDto {
    public Integer userId;
    public String addressLine1;
    public String addressLine2;
    public String city;
    public Long postalCode;
    public String country;
    public String phoneNumber;

    public UpdateAddressDto() {
    }

    public UpdateAddressDto(Integer userId, String addressLine1, String addressLine2, String city, Long postalCode, String country, String phoneNumber) {
        this.userId = userId;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    public String toString (){
        return userId + "\n" + addressLine1 + "\n" + addressLine2 + "\n" + addressLine2;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userID) {
        this.userId = userID;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Long postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
