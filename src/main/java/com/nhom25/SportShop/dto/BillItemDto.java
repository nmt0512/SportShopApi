package com.nhom25.SportShop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillItemDto {
    private Integer id;
    @JsonProperty("item")
    private ItemDto itemDto;
    private Short quantity;
    private Integer price;
}
