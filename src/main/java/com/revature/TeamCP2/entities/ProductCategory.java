/**
 * Author(s): @George Henderson
 * Contributor(s):
 * Purpose: ProductCategory Entity
 */

package com.revature.TeamCP2.entities;

import com.revature.TeamCP2.interfaces.Entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "product_categories", schema = "public")
public class ProductCategory implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(
            mappedBy = "category",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Product> productsAssociated;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column
    private byte[] image;

    public ProductCategory() {
        this.productsAssociated = new LinkedList<>();
    }

    public ProductCategory(String name, String description, byte[] image) {
        this.productsAssociated = new LinkedList<>();
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public ProductCategory addAssociatedProduct(Product product) {
        this.productsAssociated.add(product);
        return this;
    }

    public ProductCategory removeAssociatedProduct(Product product) {
        this.productsAssociated.remove(product);
        return this;
    }

    public List<Product> getProductsAssociated() {
        return productsAssociated;
    }

    public ProductCategory setProductsAssociated(List<Product> productsAssociated) {
        this.productsAssociated = productsAssociated;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
