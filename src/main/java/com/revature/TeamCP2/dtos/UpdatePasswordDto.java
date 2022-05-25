package com.revature.TeamCP2.dtos;

public class UpdatePasswordDto {

    public int userId;
    public String currentPassword;
    public String newPassword;

    public UpdatePasswordDto() {
    }

    public UpdatePasswordDto(int userId, String currentPassword, String newPassword) {
        this.userId = userId;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
