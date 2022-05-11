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
    private Long id;

    // foreign key to the cart table
    @OneToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    // table columns

    @Column(name = "date_created")
    private String dateCreated;

    // constructors
    public Order() {
    }

    public Order(Long id, Cart cart, String dateCreated) {
        this.id = id;
        this.cart = cart;
        this.dateCreated = dateCreated;
    }

    // foreign key methods

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
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
