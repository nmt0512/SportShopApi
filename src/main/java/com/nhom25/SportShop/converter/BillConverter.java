package com.nhom25.SportShop.converter;

import com.nhom25.SportShop.dto.*;
import com.nhom25.SportShop.entity.Bill;
import com.nhom25.SportShop.entity.BillItem;
import com.nhom25.SportShop.entity.User;
import com.nhom25.SportShop.repository.UserRepository;
import com.nhom25.SportShop.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BillConverter {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ItemService itemService;

    public BillDetail toBillDetail(Bill billEntity, List<BillItem> billItemEntityList) {
        BillDetail billDetail = new BillDetail();
        billDetail.setBillDto(toBillDto(billEntity));
        List<BillItemDto> billItemDtoList = new ArrayList<>();
        for (BillItem entity : billItemEntityList) {
            billItemDtoList.add(toBillItemDto(entity));
        }
        billDetail.setBillItemDtoList(billItemDtoList);
        return billDetail;
    }

    private BillDto toBillDto(Bill billEntity) {
        BillDto billDto = mapper.map(billEntity, BillDto.class);
        User user = userRepo.findByUsername(billEntity.getUsername());
        BillUserDto billUserDto = mapper.map(user, BillUserDto.class);
        billDto.setBillUserDto(billUserDto);
        return billDto;
    }

    private BillItemDto toBillItemDto(BillItem billItemEntity) {
        BillItemDto billItemDto = mapper.map(billItemEntity, BillItemDto.class);
        ItemDto itemDto = itemService.findById(billItemEntity.getItemId());
        billItemDto.setItemDto(itemDto);
        return billItemDto;
    }
}
