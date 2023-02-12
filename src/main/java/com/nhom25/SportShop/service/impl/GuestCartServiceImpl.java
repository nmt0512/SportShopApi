package com.nhom25.SportShop.service.impl;

import com.nhom25.SportShop.entity.GuestCart;
import com.nhom25.SportShop.repository.GuestCartRepository;
import com.nhom25.SportShop.response.ResponseData;
import com.nhom25.SportShop.service.GuestCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestCartServiceImpl implements GuestCartService {
    private Integer autoIncreasingId = 1;
    @Autowired
    private GuestCartRepository guestCartRepo;

    @Override
    public ResponseData<GuestCart> addGuestCart(String sessionId, GuestCart guestCart) {
        guestCart.setId(autoIncreasingId ++);
        guestCartRepo.save(sessionId, guestCart);
        ResponseData<GuestCart> data = new ResponseData<>();
        data.setData(guestCart);
        return data;
    }

    @Override
    public ResponseData<GuestCart> updateGuestCart(String sessionId, GuestCart guestCart) {
        guestCartRepo.update(sessionId, guestCart);
        ResponseData<GuestCart> data = new ResponseData<>();
        data.setData(guestCart);
        return data;
    }

    @Override
    public ResponseData<List<GuestCart>> getAllGuestCart(String sessionId) {
        ResponseData<List<GuestCart>> data = new ResponseData<>();
        data.setData(guestCartRepo.findAll(sessionId));
        return data;
    }

    @Override
    public void deleteGuestCart(String sessionId, List<Integer> guestCartIdList) {
        for(Integer guestCartId: guestCartIdList)
            guestCartRepo.deleteById(sessionId, guestCartId);
    }
}
