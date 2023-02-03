package com.nhom25.SportShop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDto {
    private String name;
    private String description;
    private String categoryCode;
    private List<String> images;
    @JsonProperty("itemDetails")
    private List<ItemDetailsDto> itemDetailsDtoList;
    private Integer price;
}
