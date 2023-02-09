package com.nhom25.SportShop.service.impl;

import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.entity.GuestCart;
import com.nhom25.SportShop.response.ResponseData;
import com.nhom25.SportShop.service.GuestCartService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class GuestCartServiceImpl implements GuestCartService {

    @Override
    public ResponseData<List<GuestCart>> save(ItemDto itemDto, HttpSession session) {
        List<GuestCart> cartList = (List<GuestCart>) session.getAttribute("CART_SESSION");
        if (cartList == null) {
            cartList = new ArrayList<>();
        }
        String itemDetail = "id:" + itemDto.getId() + "|name:" + itemDto.getName() + "|quantity:"
                + itemDto.getQuantity() + "|price:"+itemDto.getPrice();
        cartList.add(new GuestCart(session.getId(), itemDetail));
        session.setAttribute("CART_SESSION", cartList);
        ResponseData<List<GuestCart>> data = new ResponseData<>();
        data.setData(cartList);
        return data;
    }

    @Override
    public ResponseData<List<GuestCart>> findAll(HttpSession session) {
        List<GuestCart> cartList = (List<GuestCart>) session.getAttribute("CART_SESSION");
        if (cartList == null) {
            cartList = new ArrayList<>();
        }
        ResponseData<List<GuestCart>> data = new ResponseData<>();
        data.setData(cartList);
        return data;
    }

    @Override
    public void deleteAll(HttpSession session) {
        session.invalidate();
    }
}
