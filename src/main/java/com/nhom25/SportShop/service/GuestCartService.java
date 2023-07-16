package com.nhom25.SportShop.service;

import com.nhom25.SportShop.entity.GuestCart;
import com.nhom25.SportShop.response.ResponseData;

import java.util.List;

public interface GuestCartService {
    ResponseData<GuestCart> addGuestCart(String sessionId, GuestCart guestCart);

    ResponseData<GuestCart> updateGuestCart(String sessionId, GuestCart guestCart);

    ResponseData<List<GuestCart>> getAllGuestCart(String sessionId);

    void deleteGuestCart(String sessionId, List<Integer> guestCartIdList);
}
