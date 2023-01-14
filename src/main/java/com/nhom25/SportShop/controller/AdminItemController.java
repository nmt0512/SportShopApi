package com.nhom25.SportShop.controller;

import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.service.ItemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/item")
public class AdminItemController {
    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "Thêm sản phẩm")
    @PostMapping("/save")
    public ItemDto addItem(@RequestBody ItemDto itemDto){
        return itemService.saveItem(itemDto);
    }

    @ApiOperation(value = "Chỉnh sửa sản phẩm")
    @PutMapping("/save")
    public ItemDto updateItem(@RequestBody ItemDto itemDto){
        return itemService.saveItem(itemDto);
    }

    @ApiOperation(value = "Xóa sản phẩm")
    @DeleteMapping("/delete")
    public ResponseEntity deleteItem(@RequestBody List<Integer> listId){
        itemService.deleteItem(listId);
        return ResponseEntity.ok("Delete successfully");
    }
}
