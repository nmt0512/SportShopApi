package com.nhom25.SportShop.service.impl;

import com.nhom25.SportShop.converter.CartConverter;
import com.nhom25.SportShop.dto.BillDetail;
import com.nhom25.SportShop.dto.CartDto;
import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.dto.PaymentCartDto;
import com.nhom25.SportShop.entity.Cart;
import com.nhom25.SportShop.entity.Item;
import com.nhom25.SportShop.repository.CartRepository;
import com.nhom25.SportShop.repository.ItemRepository;
import com.nhom25.SportShop.repository.UserRepository;
import com.nhom25.SportShop.security.UserDetailsServiceImpl;
import com.nhom25.SportShop.service.BillService;
import com.nhom25.SportShop.service.CartService;
import com.nhom25.SportShop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepo;
    @Autowired
    private BillService billService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserDetailsServiceImpl userDetailService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartConverter converter;

    @Override
    public Cart addToCart(ItemDto itemDto, Short quantity) {
        Item item = itemRepository.findByCodeAndColorAndSize(itemDto.getCode(), itemDto.getColor(), itemDto.getSize());
        if(item.getQuantity() < quantity)
        {
            return null;
        }
        Cart cartEntity = cartRepo.findByUsernameAndItemId(userDetailService.getCurrentUsername(), item.getId());
        if(cartEntity == null)
        {
            cartEntity = new Cart();
            cartEntity.setItemId(item.getId());
            cartEntity.setQuantity(quantity);
            cartEntity.setPrice(item.getPrice() * (int)quantity);
            cartEntity.setUsername(userDetailService.getCurrentUsername());
        }
        else
        {
            short oldQuantity = cartEntity.getQuantity();
            cartEntity.setQuantity((short) (oldQuantity + quantity));
        }
        return cartRepo.save(cartEntity);
    }

    @Override
    public List<CartDto> getAllItemInCart() {
        List<CartDto> result = new ArrayList<>();
        for(Cart cart: cartRepo.findByUsername(userDetailService.getCurrentUsername()))
        {
            ItemDto itemDto = itemService.findById(cart.getItemId());
            CartDto cartDto = converter.toDto(cart);
            cartDto.setItemDto(itemDto);
            result.add(cartDto);
        }
        return result;
    }

//    @Override
//    public Cart updateCartItem(Cart cart) {
//        return cartRepo.save(cart);
//    }

    @Override
    public void deleteCartItem(Integer cartId) {
        cartRepo.deleteById(cartId);
    }

    @Override
    public BillDetail paymentCart(PaymentCartDto paymentCartDto) {
        List<Cart> cartList = paymentCartDto.getCartList();
        for(Cart cart: cartList)
        {
            cartRepo.deleteById(cart.getId());
        }
        itemService.subtractPaymentItem(cartList);
        userRepository.updateUserAddress(userDetailService.getCurrentUsername(), paymentCartDto.getAddress());
        return billService.saveBillFromCart(cartList);
    }
}
