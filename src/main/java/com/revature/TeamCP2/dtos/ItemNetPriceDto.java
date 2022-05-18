package com.revature.TeamCP2.dtos;

import com.revature.TeamCP2.entities.CartItem;

public class ItemNetPriceDto {

    public CartItem cartItem;
    public Double netPrice;

    public ItemNetPriceDto() {
    }

    public ItemNetPriceDto(CartItem cartItem, Double netPrice) {
        this.cartItem = cartItem;
        this.netPrice = netPrice;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }

    public Double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(Double netPrice) {
        this.netPrice = netPrice;
    }
}
