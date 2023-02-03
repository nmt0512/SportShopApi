package com.nhom25.SportShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDetailsDto {
    private String size;
    private String color;
    private Short quantity;
}
