package com.revature.TeamCP2.dtos;

import com.revature.TeamCP2.entities.Product;

import java.util.List;

public class OnSaleDto {

    public Integer id;
    public Double discount;
    public List<Product> productsOnSale;

    public OnSaleDto() {
    }

    public OnSaleDto(Integer id, Double discount, List<Product> productsOnSale) {
        this.id = id;
        this.discount = discount;
        this.productsOnSale = productsOnSale;
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

    public List<Product> getProductsOnSale() {
        return productsOnSale;
    }

    public void setProductsOnSale(List<Product> productsOnSale) {
        this.productsOnSale = productsOnSale;
    }
}
