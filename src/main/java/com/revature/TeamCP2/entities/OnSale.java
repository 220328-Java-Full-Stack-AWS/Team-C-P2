/**
 * Author(s): @Diego Leon
 * Contributor(s):
 * Purpose: OnSale Entity
 */

package com.revature.TeamCP2.entities;

import com.revature.TeamCP2.interfaces.Entity;

import javax.persistence.*;


/**
 * The  class for OnSale
 * <p>
 * This class is used by hibernate to create the onSaleS Table
 * </p>
 * <p>
 * This class also serves as a model to be used in service, repository, and controller.
 * </p>
 *
 * @author Diego Leon
 */
@javax.persistence.Entity
@Table(name = "On_Sale", schema = "public")
public class OnSale implements Entity {

    //This Class is the owning entity associated with the target entity (Product)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "discount_percentage")
    private Double discount;

    public OnSale() {
    }

    public OnSale(Integer id, Double discount) {
        this.id = id;
        this.discount = discount;
    }

    public OnSale(Double discount) {
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
