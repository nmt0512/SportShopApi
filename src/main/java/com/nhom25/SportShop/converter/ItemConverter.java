package com.nhom25.SportShop.converter;

import com.nhom25.SportShop.dto.ItemDetailsDto;
import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.dto.ItemRequestDto;
import com.nhom25.SportShop.entity.Item;
import com.nhom25.SportShop.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class ItemConverter {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ItemRepository itemRepository;

	public ItemDto toDto(Item entity) {
		ItemDto dto = mapper.map(entity, ItemDto.class);
		return dto;
	}

	public Item toEntity(ItemDto dto) {
		Item entity = mapper.map(dto, Item.class);
		return entity;
	}

	public List<Item> toEntityList(ItemRequestDto itemRequestDto)
	{
		List<Item> result = new ArrayList<>();
		for(ItemDetailsDto detailsDto: itemRequestDto.getItemDetailsDtoList())
		{
			Item entity = mapper.map(itemRequestDto, Item.class);
			entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			entity.setCode(convertNameToCode(entity.getName()));
			entity.setSize(detailsDto.getSize());
			entity.setColor(detailsDto.getColor());
			entity.setQuantity(detailsDto.getQuantity());
			result.add(entity);
		}
		return result;
	}

	private String convertNameToCode(String name) {
		String temp = Normalizer.normalize(name, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").toLowerCase()
				.replaceAll(" ", "-").replaceAll("đ", "d");
	}
}
