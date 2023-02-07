package com.nhom25.SportShop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nhom25.SportShop.entity.Bill;
import com.nhom25.SportShop.entity.BillItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDetail {
    @JsonProperty("bill")
    private BillDto billDto;
    @JsonProperty("billItems")
    private List<BillItemDto> billItemDtoList;
}
