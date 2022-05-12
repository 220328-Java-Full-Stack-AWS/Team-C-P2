package com.revature.TeamCP2.dtos;

import com.revature.TeamCP2.entities.User;

public class CookieDto {

    private Integer userId;
    private String firstName;
    private String lastName;

    public CookieDto() {
    }

    public CookieDto(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userId = user.getId();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
