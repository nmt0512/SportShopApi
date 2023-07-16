package com.nhom25.SportShop.service.impl;

import com.nhom25.SportShop.converter.ItemConverter;
import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.dto.ItemRequestDto;
import com.nhom25.SportShop.entity.Cart;
import com.nhom25.SportShop.entity.Category;
import com.nhom25.SportShop.entity.Image;
import com.nhom25.SportShop.entity.Item;
import com.nhom25.SportShop.repository.CategoryRepository;
import com.nhom25.SportShop.repository.ImageRepository;
import com.nhom25.SportShop.repository.ItemRepository;
import com.nhom25.SportShop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepo;
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private ImageRepository imageRepo;
    @Autowired
    private ItemConverter converter;

    @Override
    public List<ItemDto> findAllItem() {
        List<Item> entityList = itemRepo.findAll();
        return converter.toDtoList(entityList);
    }

    @Override
    public List<ItemDto> searchByName(String name) {
        List<Item> entityList = itemRepo.findByNameContaining(name);
        return converter.toDtoList(entityList);
    }

    @Override
    public List<ItemDto> findItemByGeneralCategory(String gcCode) {
        List<ItemDto> result = new ArrayList<>();
        for (Category cate : categoryRepo.findByGeneralCode(gcCode)) {
            List<Item> entityList = itemRepo.findByCategoryCode(cate.getCode());
            List<ItemDto> dtoList = converter.toDtoList(entityList);
            result.addAll(dtoList);
        }
        return result;
    }

    @Override
    public ItemDto findById(Integer itemId) {
        return converter.toDto(itemRepo.getById(itemId));
    }

    @Override
    public List<ItemDto> addItem(ItemRequestDto itemRequestDto) {
        List<ItemDto> result = new ArrayList<>();
        List<Item> itemEntityList = converter.toEntityList(itemRequestDto);
        for (Item entity : itemRepo.saveAll(itemEntityList)) {
            for (String imageLink : itemRequestDto.getImages()) {
                Image image = new Image();
                image.setItemId(entity.getId());
                image.setLink(imageLink);
                imageRepo.save(image);
            }
            ItemDto itemDto = converter.toDto(entity);
            result.add(itemDto);
        }
        return result;
    }

    @Override
    public List<ItemDto> updateItem(List<ItemDto> itemDtoList) {
        List<ItemDto> result = new ArrayList<>();
        for (ItemDto dto : itemDtoList) {
            Item newEntity = converter.toEntity(dto);
            Item oldEntity = itemRepo.getById(dto.getId());
            newEntity.setCode(convertNameToCode(newEntity.getName()));
            newEntity.setCreatedDate(oldEntity.getCreatedDate());
            newEntity.setCategoryCode(oldEntity.getCategoryCode());
            ItemDto itemDto = converter.toDto(itemRepo.save(newEntity));
            result.add(itemDto);
        }
        return result;
    }

    @Override
    public void deleteItemById(Integer itemId) {
        imageRepo.deleteInBulkByItemId(itemId);
        itemRepo.deleteById(itemId);
    }

    @Override
    public void deleteItemByCode(String code) {
        imageRepo.deleteInBulkByItemCode(code);
        itemRepo.deleteInBulkByCode(code);
    }

    @Override
    public void subtractPaymentItem(List<Cart> listCart) {
        for (Cart cart : listCart) {
            Item itemEntity = itemRepo.getById(cart.getItemId());
            itemEntity.setQuantity((short) (itemEntity.getQuantity() - cart.getQuantity()));
            itemRepo.save(itemEntity);
        }
    }

    @Override
    public List<ItemDto> filterItem(String gcCode, List<String> categoryCodeList, List<String> colorList, List<String> sizeList, Integer price) {
        List<ItemDto> result = findItemByGeneralCategory(gcCode);
        List<ItemDto> removeList = new ArrayList<>();
        for (ItemDto item : result) {
            if (categoryCodeList != null && !categoryCodeList.contains(item.getCategoryCode())) {
                removeList.add(item);
            } else if (colorList != null && !colorList.contains(item.getColor())) {
                removeList.add(item);
            } else if (sizeList != null && !sizeList.contains(item.getSize())) {
                removeList.add(item);
            } else if (price != null && item.getPrice() >= price) {
                removeList.add(item);
            }
        }
        result.removeAll(removeList);
        return result;
    }

    @Override
    public List<ItemDto> findLatestItemInWeek() {
        List<Item> entityList = itemRepo.findLatestItemInWeek();
        return converter.toDtoList(entityList);
    }

    @Override
    public List<ItemDto> findByCode(String code) {
        List<Item> entityList = itemRepo.findByCode(code);
        return converter.toDtoList(entityList);
    }

    @Override
    public List<ItemDto> getMostPopularItem() {
        List<Item> entityList = itemRepo.getMostRevenueItem();
        return converter.toDtoList(entityList);
    }

    @Override
    public List<ItemDto> getBestSellerItemInWeek() {
        List<Item> entityList = itemRepo.getBestSellerItem();
        return converter.toDtoList(entityList);
    }

    private String convertNameToCode(String name) {
        String temp = Normalizer.normalize(name, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").toLowerCase()
                .replaceAll(" ", "-").replaceAll("đ", "d");
    }

}
