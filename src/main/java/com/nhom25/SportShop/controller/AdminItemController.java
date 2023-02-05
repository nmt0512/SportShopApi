package com.nhom25.SportShop.controller;

import com.nhom25.SportShop.dto.CategoryResponse;
import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.dto.ItemRequestDto;
import com.nhom25.SportShop.response.ResponseUtils;
import com.nhom25.SportShop.service.CategoryService;
import com.nhom25.SportShop.service.ItemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/admin/item")
public class AdminItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "Thêm sản phẩm")
    @PostMapping("/add")
    public List<ItemDto> addItem(@RequestBody ItemRequestDto itemRequestDto){
        return itemService.addItem(itemRequestDto);
    }

    @ApiOperation(value = "Chỉnh sửa sản phẩm")
    @PostMapping("/update")
    public List<ItemDto> updateItem(@RequestBody List<ItemDto> itemDtoList){
        return itemService.updateItem(itemDtoList);
    }

    @ApiOperation(value = "Xóa sản phẩm")
    @PostMapping("/delete")
    public ResponseEntity deleteItem(@RequestParam("param") Integer itemId) {
        itemService.deleteItem(itemId);
        return ResponseUtils.success();
    }

    @ApiOperation(value = "Lấy tên danh mục và phân loại danh mục")
    @GetMapping("/category")
    public CategoryResponse getCategoryAndGeneralCategoryNames()
    {
        return categoryService.getCategoryAndGeneralCategoryNames();
    }
}
