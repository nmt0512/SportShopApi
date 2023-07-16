package com.nhom25.SportShop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("password")
public class UserDto {
    private String username;
    private String password;
    private String name;
    private Boolean gender;
    private String address;
    private String dobStr;
    private Timestamp dob;
    private String phone;
    private String email;
    private String avatar;
}
