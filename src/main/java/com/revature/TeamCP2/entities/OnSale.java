/**
 * Author(s): @Diego Leon
 * Contributor(s):
 * Purpose: OnSale Entity
 */

package com.revature.TeamCP2.entities;

import com.revature.TeamCP2.interfaces.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "On_Sale", schema = "public")
public class OnSale implements Entity {

    //This Class is the owning entity associated with the target entity (Product)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    @Column(name = "discount_percentage")
    private Double discount;

    //The foreign key will be stored here
    @OneToMany(mappedBy = "on_sale")
    List<Product> productsOnSale = new ArrayList<>();

    public OnSale() {
    }

    public OnSale(Integer id, List<Product> products, Double discount) {
        this.id = id;
        this.productsOnSale = products;
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Product> getProductsOnSale() {
        return productsOnSale;
    }

    public void setProductsOnSale(List<Product> products) {
        this.productsOnSale = products;
    }

    public OnSale addOnSaleProduct(Product product) {
        this.productsOnSale.add(product);
        return this;
    }

    public OnSale removeOnSaleProduct(Product product) {
        this.productsOnSale.remove(product);
        return this;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
