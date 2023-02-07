package com.nhom25.SportShop.service.impl;

import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.entity.GuestCart;
import com.nhom25.SportShop.entity.Item;
import com.nhom25.SportShop.repository.GuestCartRepository;
import com.nhom25.SportShop.repository.ItemRepository;
import com.nhom25.SportShop.service.GuestCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GuestCartServiceImpl implements GuestCartService {
    private Integer autoIncreasingId = 1;
    @Autowired
    private GuestCartRepository guestCartRepo;
    @Autowired
    private ItemRepository itemRepo;

    @Override
    public GuestCart addGuestCart(ItemDto itemDto, Short quantity) {
        Item itemEntity = itemRepo.findByCodeAndColorAndSize(itemDto.getCode(), itemDto.getColor(), itemDto.getSize());
        if(itemEntity.getQuantity() - quantity < 0)
        {
            return null;
        }
        for(GuestCart cart: getAllGuestCart())
        {
            if(Objects.equals(itemEntity.getId(), cart.getItemEntity().getId()))
            {
                cart.setQuantity((short) (cart.getQuantity() + quantity));
                return updateGuestCart(cart);
            }
        }
        GuestCart guestCart = new GuestCart();
        guestCart.setId(autoIncreasingId ++);
        guestCart.setItemEntity(itemEntity);
        guestCart.setQuantity(quantity);
        guestCart.setPrice(itemEntity.getPrice() * (int)quantity);
        return guestCartRepo.save(guestCart);
    }

    @Override
    public GuestCart updateGuestCart(GuestCart guestCart) {
        if(guestCart.getItemEntity().getQuantity() - guestCart.getQuantity() < 0)
        {
            return null;
        }
        return guestCartRepo.save(guestCart);
    }

    @Override
    public List<GuestCart> getAllGuestCart() {
        return guestCartRepo.findAll();
    }

    @Override
    public Boolean deleteGuestCartById(Integer id) {
        return guestCartRepo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        guestCartRepo.deleteAll();
    }
}
