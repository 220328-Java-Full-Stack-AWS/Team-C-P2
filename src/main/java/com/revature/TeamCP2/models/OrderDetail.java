/**
 * Author(s): @Arun Mohan
 * Contributor(s):
 * Purpose: Model class used to represent an Order Detail item to be persisted in the database.
 */

package com.revature.TeamCP2.models;

import com.revature.TeamCP2.interfaces.Model;

import javax.persistence.*;

@Entity
@Table(name = "order_detail", schema="public")
public class OrderDetail implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Long id;

    // foreign key to the order table
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    public Order order;

    // table columns

    @Column(name = "details")
    private String details;

    // constructors

    public OrderDetail() {
    }

    public OrderDetail(Long id, Order order, String details) {
        this.id = id;
        this.order = order;
        this.details = details;
    }

    // foreign keys

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    // getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
