package com.nhom25.SportShop.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
	private Integer id;
	private String name;
	private String description;
	private String categoryName;
	private String categoryCode;
	private List<String> image;
	private String size;
	private String color;
	private String type;
	private Short quantity;
	private Integer price;
	private Timestamp createdDate;
    
}
