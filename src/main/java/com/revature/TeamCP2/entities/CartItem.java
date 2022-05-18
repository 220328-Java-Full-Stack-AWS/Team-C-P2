package com.revature.TeamCP2.entities;

import com.revature.TeamCP2.interfaces.Entity;

import javax.persistence.*;

/**
Author: Steven Dowd
Purpose: Model for cart item objects
 */

@javax.persistence.Entity
@Table(name = "cart_items", schema = "public")
public class CartItem implements Entity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @ManyToOne
    @JoinColumn (name = "cart_id")
    private Cart cart;

    @OneToOne
    @JoinColumn (name = "product_id")
    private Product product;

    @Column
    private Integer quantity;

    @Column
    private Double netPrice;

    public CartItem() {
    }

    public CartItem(Cart cart, Product product, Integer quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    public CartItem(Cart cart, Product product, Integer quantity, Double netPrice) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.netPrice = netPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(Double netPrice) {
        this.netPrice = netPrice;
    }
}




