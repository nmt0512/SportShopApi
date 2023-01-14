package com.nhom25.SportShop.dto;

import java.io.Serializable;

public class LoginResponseDto implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;
    private Boolean isAdmin;

    public LoginResponseDto(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public LoginResponseDto(String jwtToken, Boolean isAdmin) {
        this.jwtToken = jwtToken;
        this.isAdmin = isAdmin;
    }

    public String getToken() {
        return this.jwtToken;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }
}