/**
 * Author(s): @George Henderson
 * Contributor(s):
 * Purpose: Payment Entity
 */

package com.revature.TeamCP2.entities;

import com.revature.TeamCP2.interfaces.Entity;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "payments", schema = "public")
public class Payment implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "payment_type", nullable = false)
    // Visa, Amex, Discover, Mastercard, etc.
    private String network;

    @Column
    // Capital One, US Bank, Chase, etc.
    private String issuer;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "security_code", nullable = false)
    private Integer securityCode;

    @Column(name = "expiration_date", nullable = false)
    private String expirationDate;

    public Payment() {
    }

    public Payment(String network, String issuer, String cardNumber, Integer securityCode, String expirationDate) {
        this.network = network;
        this.issuer = issuer;
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.expirationDate = expirationDate;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String paymentType) {
        this.network = paymentType;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String provider) {
        this.issuer = provider;
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

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
