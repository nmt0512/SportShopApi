package com.nhom25.SportShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private Integer id;
    private ItemDto itemDto;
    private String username;
    private Short quantity;
    private Integer price;
}
