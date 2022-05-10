/**
 * Author(s): @Brandon Le
 * Contributor(s):
 * Purpose: Generates table model for Products.
 *          The table 'Products' has a many-to-one relation with 'Category' and 'On_sale'
 *          and a one-to-one relation with 'Cart_item'.
 *
 */

package com.revature.TeamCP2.models;

import com.revature.TeamCP2.interfaces.Model;

import javax.persistence.*;

@Entity(name = "Products")
@Table(name = "products", schema = "public")
public class Product implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "descr")
    private String descr;

    @Column(name = "price")
    private double price;

    @ManyToOne()
    @JoinColumn(name = "on_sale", nullable = true)
    private OnSale on_sale;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    @Column(name = "is_featured")
    private boolean is_featured;

    @Column(name = "image")
    private byte[] image;

    public Product() {
    }

    public Product(Integer id, String name, String descr, double price, ProductCategory category, boolean is_featured, byte[] image) {
        this.id = id;
        this.name = name;
        this.descr = descr;
        this.price = price;
        this.category = category;
        this.is_featured = is_featured;
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


    public boolean isIs_featured() {
        return is_featured;
    }

    public void setIs_featured(boolean is_featured) {
        this.is_featured = is_featured;
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
}
