package com.nhom25.SportShop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDto {
    private Integer id;
    private Integer totalPrice;
    private Timestamp time;

    @JsonProperty("userInfo")
    private BillUserDto billUserDto;

    private Boolean confirm;
    private Boolean status;
}
