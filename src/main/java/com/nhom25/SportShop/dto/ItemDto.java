package com.nhom25.SportShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("itemDto")
public class ItemDto {
    private Integer id;
    private String name;
    private String code;
    private String description;
    private String categoryCode;
    private List<String> image;
    private String size;
    private String color;
    private String type;
    private Short quantity;
    private Integer price;
    private Timestamp createdDate;
}
