/**
 * Author(s): @Diego Leon
 * Contributor(s):
 * Purpose: UserAddress Entity
 */

package com.revature.TeamCP2.entities;

import com.revature.TeamCP2.interfaces.Entity;

import javax.persistence.*;


/**
 * The  class for User Address
 * <p>
 * This class is used by hibernate to create the User Address Table
 * </p>
 * <p>
 * This class also serves as a model to be used in service, repository, and controller.
 * </p>
 *
 * @author Diego Leon
 */
@javax.persistence.Entity
@Table(name = "User_Address", schema = "public")
public class UserAddress implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "address_line1")
    private String addressLine1;
    @Column(name = "address_line2")
    private String addressLine2;
    @Column(name = "city")
    private String city;
    @Column(name = "postal_code")
    private Long postalCode;
    @Column(name = "country")
    private String country;
    @Column(name = "phone_number")
    private String phoneNumber;

    public UserAddress() {
    }

    public UserAddress(Integer id, String addressLine1, String addressLine2, String city, Long postalCode, String country, String phoneNumber) {
        this.id = id;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer addressID) {
        this.id = addressID;
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

