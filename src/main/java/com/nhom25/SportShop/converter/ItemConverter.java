package com.nhom25.SportShop.converter;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.entity.Item;

@Component
public class ItemConverter {
	@Autowired
	private ModelMapper mapper;

	public ItemDto toDto(Item entity) {
		ItemDto dto = mapper.map(entity, ItemDto.class);
		return dto;
	}

	public Item toEntity(ItemDto dto) {
		Item entity = mapper.map(dto, Item.class);
		return entity;
	}
}
