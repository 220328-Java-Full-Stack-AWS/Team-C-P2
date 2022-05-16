package com.revature.TeamCP2.entities;

import com.revature.TeamCP2.interfaces.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
Author: Steven Dowd
Purpose: Model for cart objects
 */

@javax.persistence.Entity
@Table(name = "cart", schema = "public")
public class Cart implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<CartItem> cartItems;

    @Column
    private Double total;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    public Cart(User user, Double total) {
        this.user = user;
        this.total = total;
    }

    public Cart(User user, List<CartItem> cartItems, Double total) {
        this.user = user;
        this.cartItems = cartItems;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }

    public void removeCartItem(Integer id) {

        for (CartItem c : this.cartItems) {
            if (c.getId() == id) {
                this.cartItems.remove(c);
            }
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
