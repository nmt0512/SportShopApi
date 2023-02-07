package com.nhom25.SportShop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillUserDto {
    private String username;
    @JsonProperty("fullname")
    private String name;
    private String address;
    private String phone;
}
