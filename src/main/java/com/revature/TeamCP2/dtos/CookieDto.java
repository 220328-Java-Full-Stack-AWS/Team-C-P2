package com.revature.TeamCP2.dtos;

import com.revature.TeamCP2.entities.User;
import com.revature.TeamCP2.interfaces.Role;

public class CookieDto {

    private Integer userId;
    private Role userRole;
    private String firstName;
    private String lastName;

    public CookieDto() {
    }

    public CookieDto(User user) {
        this.firstName = user.getFirstName();
        this.userRole = user.getRole();
        this.lastName = user.getLastName();
        this.userId = user.getId();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
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
