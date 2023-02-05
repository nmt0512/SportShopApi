package com.nhom25.SportShop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    @JsonProperty("generalCategoryNames")
    private List<String> generalCategoryNameList;
    @JsonProperty("categoryNames")
    private List<String> categoryNameList;
}
