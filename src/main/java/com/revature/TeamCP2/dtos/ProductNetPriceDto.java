package com.revature.TeamCP2.dtos;

import com.revature.TeamCP2.entities.Product;

public class ProductNetPriceDto {

    public Product product;
    public Double netPrice;

    public ProductNetPriceDto() {
    }

    public ProductNetPriceDto(Product product, Double netPrice) {
        this.product = product;
        this.netPrice = netPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(Double netPrice) {
        this.netPrice = netPrice;
    }




}
