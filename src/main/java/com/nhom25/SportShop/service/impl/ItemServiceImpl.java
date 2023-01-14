package com.nhom25.SportShop.service.impl;

import com.nhom25.SportShop.converter.ItemConverter;
import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.entity.Cart;
import com.nhom25.SportShop.entity.Category;
import com.nhom25.SportShop.entity.Image;
import com.nhom25.SportShop.entity.Item;
import com.nhom25.SportShop.repository.ImageRepository;
import com.nhom25.SportShop.repository.ItemRepository;
import com.nhom25.SportShop.service.CategoryService;
import com.nhom25.SportShop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ItemConverter converter;
	@Autowired
	private ImageRepository imageRepo;

	@Override
	public List<ItemDto> findAllItem() {
		List<ItemDto> result = new ArrayList<>();
		for(Item e: itemRepo.findAll())
		{
			ItemDto dto = converter.toDto(e);
			List<Image> listImage = imageRepo.findByItemId(e.getId());
			dto = setImageForDto(dto, listImage);
			result.add(dto);
		}
		return result;
	}

	@Override
	public List<ItemDto> findItemByCategory(String categoryCode) {
		List<ItemDto> result = new ArrayList<>();
		for(Item e: itemRepo.findByCategoryCode(categoryCode))
		{
			ItemDto dto = converter.toDto(e);
			List<Image> listImage = imageRepo.findByItemId(e.getId());
			dto = setImageForDto(dto, listImage);
			result.add(dto);
		}
		return result;
	}

	@Override
	public List<ItemDto> searchByName(String name) {
		List<ItemDto> result = new ArrayList<>();
		for(Item e: itemRepo.findByNameContaining(name))
		{
			ItemDto dto = converter.toDto(e);
			List<Image> listImage = imageRepo.findByItemId(e.getId());
			dto = setImageForDto(dto, listImage);
			result.add(dto);
		}
		return result;
	}

	@Override
	public List<ItemDto> findItemByGeneralCategory(String gcCode) {
		List<ItemDto> result = new ArrayList<>();
		for(Category cate: categoryService.findByGeneralCode(gcCode))
		{
			for(Item e: itemRepo.findByCategoryCode(cate.getCode()))
			{
				ItemDto dto = converter.toDto(e);
				List<Image> listImage = imageRepo.findByItemId(e.getId());
				dto = setImageForDto(dto, listImage);
				result.add(dto);
			}
		}
		return result;
	}

	@Override
	public ItemDto findById(Integer itemId) {
		ItemDto dto = converter.toDto(itemRepo.getById(itemId));
		List<Image> listImage = imageRepo.findByItemId(itemId);
		dto = setImageForDto(dto, listImage);
		return dto;
	}

	@Override
	public ItemDto saveItem(ItemDto itemDto) {
		Item itemEntity = converter.toEntity(itemDto);
		if(itemEntity.getId() == null)
		{
			itemEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		}
		itemEntity.setCode(convertNameToCode(itemEntity.getName()));
		return converter.toDto(itemRepo.save(itemEntity));
	}

	@Override
	public void deleteItem(List<Integer> listItemId) {
		for(Integer id: listItemId)
		{
			itemRepo.deleteById(id);
		}
	}

	@Override
	public void subtractPaymentItem(List<Cart> listCart) {
		for(Cart cart: listCart)
		{
			Item itemEntity = itemRepo.getById(cart.getItemId());
			itemEntity.setQuantity((short)(itemEntity.getQuantity() - cart.getQuantity()));
			itemRepo.save(itemEntity);
		}
	}

	@Override
	public Short getItemQuantity(Integer id) {
		return itemRepo.getById(id).getQuantity();
	}


	private String convertNameToCode(String name){
		String temp = Normalizer.normalize(name, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").toLowerCase()
				.replaceAll(" ", "-").replaceAll("đ", "d");
	}
	private ItemDto setImageForDto(ItemDto dto, List<Image> listImage)
	{
		List<String> listLink = new ArrayList<>();
		for(Image image: listImage)
		{
			listLink.add(image.getLink());
		}
		dto.setImage(listLink);
		return dto;
	}

}
