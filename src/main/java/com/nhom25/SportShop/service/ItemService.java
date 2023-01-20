package com.nhom25.SportShop.service;

import java.util.List;

import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.entity.Cart;

public interface ItemService {
	List<ItemDto> findAllItem();
	List<ItemDto> searchByName(String name);
	List<ItemDto> findItemByGeneralCategory(String gcCode);
	ItemDto findById(Integer itemId);
	ItemDto saveItem(ItemDto itemDto);
	void deleteItem(List<Integer> listItemId);
	void subtractPaymentItem(List<Cart> listCart);
	Short getItemQuantity(Integer id);
	List<ItemDto> filterItem(String gcCode, List<String> categoryCodeList, List<String> colorList, Integer price);
}
