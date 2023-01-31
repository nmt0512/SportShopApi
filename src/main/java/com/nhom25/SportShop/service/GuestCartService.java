package com.nhom25.SportShop.service;

import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.entity.GuestCart;

import java.util.List;

public interface GuestCartService {
    GuestCart addGuestCart(ItemDto itemDto, Short quantity);
    GuestCart updateGuestCart(GuestCart guestCart);
    List<GuestCart> getAllGuestCart();
    Boolean deleteGuestCartById(Integer id);
    void deleteAll();
}
