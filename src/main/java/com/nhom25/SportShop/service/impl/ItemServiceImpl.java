package com.nhom25.SportShop.service.impl;

import com.nhom25.SportShop.converter.ItemConverter;
import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.dto.ItemRequestDto;
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

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ItemServiceImpl implements ItemService {
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
        for (Item e : itemRepo.findAll()) {
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
        for (Item e : itemRepo.findByNameContaining(name)) {
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
        for (Category cate : categoryService.findByGeneralCode(gcCode)) {
            for (Item e : itemRepo.findByCategoryCode(cate.getCode())) {
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
    public List<ItemDto> addItem(ItemRequestDto itemRequestDto) {
        List<ItemDto> result = new ArrayList<>();
        List<Item> itemEntityList = converter.toEntityList(itemRequestDto);
        for(Item entity: itemRepo.saveAll(itemEntityList))
        {
            for(String imageLink: itemRequestDto.getImages())
            {
                Image image = new Image();
                image.setItemId(entity.getId());
                image.setLink(imageLink);
                imageRepo.save(image);
            }
            ItemDto itemDto = converter.toDto(entity);
            itemDto.setImage(itemRequestDto.getImages());
            result.add(itemDto);
        }
        return result;
    }

    @Override
    public List<ItemDto> updateItem(List<ItemDto> itemDtoList) {
        List<ItemDto> result = new ArrayList<>();
        for(ItemDto dto: itemDtoList)
        {
            Item newEntity = converter.toEntity(dto);
            Item oldEntity = itemRepo.getById(dto.getId());
            newEntity.setCode(convertNameToCode(newEntity.getName()));
            newEntity.setCreatedDate(oldEntity.getCreatedDate());
            newEntity.setCategoryCode(oldEntity.getCategoryCode());
            result.add(converter.toDto(itemRepo.save(newEntity)));
        }
        return result;
    }

    @Override
    public void deleteItem(Integer itemId) {
        imageRepo.deleteByItemId(itemId);
        itemRepo.deleteById(itemId);
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
    public List<ItemDto> filterItem(String gcCode, List<String> categoryCodeList, List<String> colorList, Integer price) {
        List<ItemDto> result = findItemByGeneralCategory(gcCode);
        List<ItemDto> removeList = new ArrayList<>();
        for (ItemDto item : result) {
            if (categoryCodeList != null && !categoryCodeList.contains(item.getCategoryCode()))
            {
                removeList.add(item);
                continue;
            }
            if (colorList != null && !colorList.contains(item.getColor()))
            {
                removeList.add(item);
                continue;
            }
            if(price != null && item.getPrice() >= price)
            {
                removeList.add(item);
            }
        }
        result.removeAll(removeList);
        return result;
    }

    @Override
    public List<ItemDto> findLatestItemInWeek() {
        List<ItemDto> result = new ArrayList<>();
        for (Item e : itemRepo.findLatestItemInWeek()) {
            ItemDto dto = converter.toDto(e);
            List<Image> listImage = imageRepo.findByItemId(e.getId());
            dto = setImageForDto(dto, listImage);
            result.add(dto);
        }
        return result;
    }

    private ItemDto setImageForDto(ItemDto dto, List<Image> listImage) {
        List<String> listLink = new ArrayList<>();
        for (Image image : listImage) {
            listLink.add(image.getLink());
        }
        dto.setImage(listLink);
        return dto;
    }

    private String convertNameToCode(String name) {
        String temp = Normalizer.normalize(name, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").toLowerCase()
                .replaceAll(" ", "-").replaceAll("đ", "d");
    }

}
