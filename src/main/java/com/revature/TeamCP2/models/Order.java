/**
 * Author(s): @Arun Mohan
 * Contributor(s):
 * Purpose: Model class used to represent an Order to be persisted in the database.
 */

package com.revature.TeamCP2.models;

import com.revature.TeamCP2.interfaces.Model;

import javax.persistence.*;

@Entity
@Table(name = "order", schema="public")
public class Order implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    // foreign key to the cart table
    @OneToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    // foreign key to the order detail
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_detail_id", referencedColumnName = "id")
    private OrderDetail detail;

    // table columns

    @Column(name = "date_created")
    private String dateCreated;

    // constructors

    public Order() {
    }

    public Order(Long id, Cart cart, OrderDetail detail, String dateCreated) {
        this.id = id;
        this.cart = cart;
        this.detail = detail;
        this.dateCreated = dateCreated;
    }

    // foreign key methods

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public OrderDetail getDetail() {
        return detail;
    }

    public void setDetail(OrderDetail detail) {
        this.detail = detail;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
