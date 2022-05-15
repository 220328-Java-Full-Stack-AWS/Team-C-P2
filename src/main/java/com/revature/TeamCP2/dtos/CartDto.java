package com.revature.TeamCP2.dtos;

import com.revature.TeamCP2.entities.CartItem;

import java.util.List;

public class CartDto {
    public Integer userID;
    public List<CartItem> items;
    public double total;

    public CartDto() {
    }

    public CartDto(Integer userID, List<CartItem> items, double total) {
        this.userID = userID;
        this.items = items;
        this.total = total;
    }

}