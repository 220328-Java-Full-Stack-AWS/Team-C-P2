package com.revature.TeamCP2.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.TeamCP2.interfaces.Entity;
import com.revature.TeamCP2.interfaces.Role;

@javax.persistence.Entity
@Table (name = "users", schema = "public")
public class User implements Entity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private Role role;

    @Column(name = "activeCartID")
    Integer activeCartId;

    @OneToOne()
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private UserAddress userAddress;

    //User one to many relationship with payment
    @OneToOne()
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;
    @Basic
    @Column(name = "date_created",updatable = false)
    private String dateCreated;
    @Basic
    @Column(name = "date_modifies")
    private Date dateModifies;
    //Both @OneToMany needs Testing------------

    public User() {
    }

    public User(Integer id, String username, String password, String firstName, String lastName, String email, Role role, Integer activeCartId, UserAddress userAddress, Payment payment, String dateCreated, Date dateModifies, Collection<Cart> cartsById, Collection<Order> orderById) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.activeCartId = activeCartId;
        this.userAddress = userAddress;
        this.payment = payment;
        this.dateCreated = dateCreated;
        this.dateModifies = dateModifies;
    }

    //---------------------------------------------
    //User one to many relationship to Payment
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
//----------------------------------------------------------------------


    public Integer getActiveCartId() {
        return activeCartId;
    }

    public void setActiveCartId(Integer activeCartId) {
        this.activeCartId = activeCartId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModifies() {
        return dateModifies;
    }

    public void setDateModifies(Date dateModifies) {
        this.dateModifies = dateModifies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (dateCreated != null ? !dateCreated.equals(user.dateCreated) : user.dateCreated != null) return false;
        if (dateModifies != null ? !dateModifies.equals(user.dateModifies) : user.dateModifies != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (dateModifies != null ? dateModifies.hashCode() : 0);
        return result;
    }
}