package com.nhom25.SportShop.service;

import com.nhom25.SportShop.dto.BillDetail;
import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.dto.PaymentCartDto;
import com.nhom25.SportShop.entity.Cart;

import java.util.List;

public interface CartService {
    Cart addToCart(ItemDto itemDto, Short quantity);
    List<Cart> getAllItemInCart();
    Cart updateCartItem(Cart cart);
    void deleteCartItem(Integer cartId);
    BillDetail paymentCart(PaymentCartDto paymentCartDto);
}
