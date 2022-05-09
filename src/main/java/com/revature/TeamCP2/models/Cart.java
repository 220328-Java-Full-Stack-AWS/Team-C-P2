package com.revature.TeamCP2.models;

import com.revature.TeamCP2.interfaces.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
Author: Steven Dowd
Purpose: Model for cart objects
 */

@Entity
@Table(name = "cart", schema = "public")
public class Cart implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user-id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    @Column(name = "cart_item")
    private List<CartItem> cartItems;

    @Column
    private Double total;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    public Cart(User user, Double total) {
        cartItems = new ArrayList<>();
        this.user = user;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }

    public void removeCartItem(CartItem cartItem) {
        this.cartItems.remove(cartItem);
    }

    public User getUserId() {
        return user;
    }

    public void setUserId(User user) {
        this.user = user;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
