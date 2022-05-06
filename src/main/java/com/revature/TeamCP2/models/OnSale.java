package com.revature.TeamCP2.models;

import com.revature.TeamCP2.interfaces.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "On_Sale", schema = "public")
public class OnSale implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long id;

    //This Class is the owning entity associated with the target entity (Product)
    //The foreign key will be stored here
    @OneToMany
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    List<Product> products = new ArrayList<>();


    @Column(name = "product_id")
    private Long productId;


    @Column(name = "discount_percentage")
    private Double discount;

    public OnSale() {
    }

    public OnSale(Long id, Long productId, Double discount) {
        this.id = id;
        this.productId = productId;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
