package com.revature.TeamCP2.dtos;

import com.revature.TeamCP2.entities.CartItem;

import java.util.List;

public class CartDto {
    public Integer userId;
    public String dateCreated;

    public CartDto() {
    }

    public CartDto(Integer userId) {
        this.userId = userId;

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}