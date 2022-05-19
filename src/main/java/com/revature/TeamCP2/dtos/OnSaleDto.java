package com.revature.TeamCP2.dtos;

import com.revature.TeamCP2.entities.Product;

import java.util.List;

public class OnSaleDto {

    public Integer id;
    public Double discount;
    public List<Product> productsOnSale;

    public OnSaleDto() {
    }

    public OnSaleDto(Integer id, Double discount) {
        this.id = id;
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
