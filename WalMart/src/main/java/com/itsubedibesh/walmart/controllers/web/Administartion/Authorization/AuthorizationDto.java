package com.itsubedibesh.walmart.controllers.web.Administartion.Authorization;

public class AuthorizationDto {
    public AuthorizationDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public AuthorizationDto() {
    }

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
