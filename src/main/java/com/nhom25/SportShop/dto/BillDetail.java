package com.nhom25.SportShop.dto;

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
    private Bill bill;
    private List<BillItem> listBillItem = new ArrayList<>();
}
