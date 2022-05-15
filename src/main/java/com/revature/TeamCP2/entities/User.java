package com.revature.TeamCP2.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;
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
    Integer activeCartID;

    @OneToOne()
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private UserAddress userAddresses;

    //User one to many relationship with payment
    @OneToOne()
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payments;
    @Basic
    @Column(name = "date_created",updatable = false)
    private String dateCreated;
    @Basic
    @Column(name = "date_modifies")
    private Date dateModifies;
    //Both @OneToMany needs Testing------------
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private Collection<Cart> cartsById;
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private Collection<Order> orderById;


    public User() {
    }

    public User(Integer id, String username, String password, String firstName, String lastName, String email, Role role, Integer activeCartID, UserAddress userAddresses, Payment payments, String dateCreated, Date dateModifies, Collection<Cart> cartsById, Collection<Order> orderById) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.activeCartID = activeCartID;
        this.userAddresses = userAddresses;
        this.payments = payments;
        this.dateCreated = dateCreated;
        this.dateModifies = dateModifies;
        this.cartsById = cartsById;
        this.orderById = orderById;
    }

    //---------------------------------------------
    //User one to many relationship to Payment
    public Payment getPayments() {
        return payments;
    }

    public void setPayments(Payment payments) {
        this.payments = payments;
    }
//----------------------------------------------------------------------


    public Integer getActiveCartID() {
        return activeCartID;
    }

    public void setActiveCartID(Integer activeCartID) {
        this.activeCartID = activeCartID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
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

    public UserAddress getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(UserAddress userAddresses) {
        this.userAddresses = userAddresses;
    }

    //    public Integer getAddressId() {
//        return addressId;
//    }
//
//    public void setAddressId(Integer addressId) {
//        this.addressId = addressId;
//    }
    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
//    public String getPaymentId() {
//        return paymentId;
//    }
//
//    public void setPaymentId(String paymentId) {
//        this.paymentId = paymentId;
//    }

//    public Date getDateCreated() {
//        return dateCreated;
//    }
//
//    public void setDateCreated(Date dateCreated) {
//        this.dateCreated = dateCreated;
//    }

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
//        if (addressId != null ? !addressId.equals(user.addressId) : user.addressId != null) return false;
//        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
//        if (paymentId != null ? !paymentId.equals(user.paymentId) : user.paymentId != null) return false;
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
//        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
//        result = 31 * result + (phone != null ? phone.hashCode() : 0);
//        result = 31 * result + (paymentId != null ? paymentId.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (dateModifies != null ? dateModifies.hashCode() : 0);
        return result;
    }

    public Collection<Cart> getCartsById() {
        return cartsById;
    }

    public void setCartsById(Collection<Cart> cartsById) {
        this.cartsById = cartsById;
    }

    public Collection<Order> getOrderById() {
        return orderById;
    }

    public void setOrderById(Collection<Order> orderById) {
        this.orderById = orderById;
    }
}