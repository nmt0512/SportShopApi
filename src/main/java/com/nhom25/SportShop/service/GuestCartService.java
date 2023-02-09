package com.nhom25.SportShop.service;

import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.entity.GuestCart;
import com.nhom25.SportShop.response.ResponseData;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface GuestCartService {
    ResponseData<List<GuestCart>> save(ItemDto itemDto, HttpSession session);
    ResponseData<List<GuestCart>> findAll(HttpSession session);
    void deleteAll(HttpSession session);
}
