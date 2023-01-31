package com.nhom25.SportShop.converter;

import com.nhom25.SportShop.dto.CartDto;
import com.nhom25.SportShop.entity.Cart;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartConverter {
    @Autowired
    private ModelMapper mapper;

    public CartDto toDto(Cart entity) {
        CartDto dto = mapper.map(entity, CartDto.class);
        return dto;
    }
}
