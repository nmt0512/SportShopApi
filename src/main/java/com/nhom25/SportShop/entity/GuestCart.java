package com.nhom25.SportShop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("GuestCart")
public class GuestCart implements Serializable {
    private static final long serialVersionUID = -7817224776021728682L;

    private Integer id;
    private Integer itemId;
    private String name;
    private String code;
    private String size;
    private String color;
    private String type;
    private Short quantity;
    private Integer price;
}
