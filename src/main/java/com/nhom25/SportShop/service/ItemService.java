package com.nhom25.SportShop.service;

import java.util.List;

import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.dto.ItemRequestDto;
import com.nhom25.SportShop.entity.Cart;

public interface ItemService {
    List<ItemDto> findAllItem();

    List<ItemDto> searchByName(String name);

    List<ItemDto> findItemByGeneralCategory(String gcCode);

    ItemDto findById(Integer itemId);

    List<ItemDto> addItem(ItemRequestDto itemRequestDto);

    List<ItemDto> updateItem(List<ItemDto> itemDtoList);

    void deleteItemById(Integer itemId);

    void deleteItemByCode(String code);

    void subtractPaymentItem(List<Cart> listCart);

    List<ItemDto> filterItem(String gcCode, List<String> categoryCodeList, List<String> colorList, List<String> sizeList, Integer price);

    List<ItemDto> findLatestItemInWeek();

    List<ItemDto> findByCode(String code);

    List<ItemDto> getMostPopularItem();

    List<ItemDto> getBestSellerItemInWeek();
}
