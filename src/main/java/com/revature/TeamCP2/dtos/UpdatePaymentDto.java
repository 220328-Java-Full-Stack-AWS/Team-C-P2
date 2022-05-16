package com.revature.TeamCP2.dtos;

public class UpdatePaymentDto {
    public Integer userID;
    public String network;
    public String issuer;
    public Integer cardNumber;
    public Short securityCode;
    public String expDate;

    public UpdatePaymentDto() {
    }

    public UpdatePaymentDto(Integer userID, String network, String issuer, Integer cardNumber, Short securityCode, String expDate) {
        this.userID = userID;
        this.network = network;
        this.issuer = issuer;
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.expDate = expDate;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Short getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(Short securityCode) {
        this.securityCode = securityCode;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }
}
