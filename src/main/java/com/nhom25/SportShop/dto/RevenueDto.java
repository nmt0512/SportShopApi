package com.nhom25.SportShop.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevenueDto {
    @SerializedName("MonthTime")
    private String monthTime;
    @SerializedName("TotalRevenue")
    private Integer totalRevenue;
}
