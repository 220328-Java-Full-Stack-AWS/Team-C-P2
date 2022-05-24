package com.revature.TeamCP2.dtos;

public class UpdatePaymentDto {
    public Integer userId;
    public String network;
    public String issuer;
    public String cardNumber;
    public Integer securityCode;
    public String expDate;

    public UpdatePaymentDto() {
    }

    public UpdatePaymentDto(Integer userId, String network, String issuer, String cardNumber, Integer securityCode, String expDate) {
        this.userId = userId;
        this.network = network;
        this.issuer = issuer;
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.expDate = expDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(Integer securityCode) {
        this.securityCode = securityCode;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }
}
