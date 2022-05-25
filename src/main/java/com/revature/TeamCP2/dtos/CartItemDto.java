package com.revature.TeamCP2.dtos;

public class CartItemDto {

    public Integer userId;
    public Integer productId;
    public Integer quantity;
    public Double netPrice;

    public CartItemDto() {
    }

    public CartItemDto(Integer userId, Integer productId, Integer quantity, Double netPrice) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.netPrice = netPrice;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(Double netPrice) {
        this.netPrice = netPrice;
    }
}
