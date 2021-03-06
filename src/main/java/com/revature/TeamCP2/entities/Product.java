/**
 * Author(s): @Brandon Le
 * Contributor(s): @Arun Mohan
 * Purpose: Generates table model for Products.
 *          The table 'Products' has a many-to-one relation with 'Category' and 'On_sale'
 *          and a one-to-one relation with 'Cart_item'.
 *
 */

package com.revature.TeamCP2.entities;

import com.revature.TeamCP2.interfaces.Entity;

import javax.persistence.*;

@javax.persistence.Entity(name = "Products")
@Table(name = "products", schema = "public")
public class Product implements Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "descr", length=500)
    private String descr;

    @Column(name = "price")
    private double price;

    @ManyToOne()//cascade = CascadeType.REMOVE
    @JoinColumn(name = "on_sale", nullable = true)
    private OnSale onSale;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    @Column(name = "is_featured")
    private boolean isFeatured;

    @Column(name = "image")
    private byte[] image;

    public Product() {
    }

    public Product(Integer id, String name, String descr, double price, ProductCategory category, boolean isFeatured, byte[] image) {
        this.id = id;
        this.name = name;
        this.descr = descr;
        this.price = price;
        this.category = category;
        this.isFeatured = isFeatured;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public OnSale getOnSale() { return this.onSale; }

    public void setOnSale(OnSale onSale) { this.onSale = onSale; }
}
