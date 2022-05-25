package com.revature.TeamCP2.dtos;

public class UpdateItemDto {

    public Integer cartItemId;
    public Integer quantity;

    public UpdateItemDto() {
    }

    public UpdateItemDto(Integer cartItemId, Integer quantity) {
        this.cartItemId = cartItemId;
        this.quantity = quantity;
    }

    public Integer getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Integer cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
