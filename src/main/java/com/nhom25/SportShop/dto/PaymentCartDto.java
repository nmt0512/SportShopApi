package com.nhom25.SportShop.dto;

import com.nhom25.SportShop.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCartDto {
    private String address;
    private List<Cart> cartList;
}
