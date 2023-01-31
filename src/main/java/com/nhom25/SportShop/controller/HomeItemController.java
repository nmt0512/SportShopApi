package com.nhom25.SportShop.controller;

import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.service.ItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeItemController {
	@Autowired
	private ItemService itemService;

	@ApiOperation(value = "Lấy thông tin tất cả sản phẩm")
	@GetMapping("/all")
	public List<ItemDto> viewAllItem(){
		return itemService.findAllItem();
	}

	@ApiOperation(value = "Lấy thông tin sản phẩm theo phân loại chung")
	@GetMapping("/general/{gc-code}")
	public List<ItemDto> viewItemByGeneralCategory(
			@ApiParam(value = "Code phân loại chung sản phẩm", defaultValue = "trang-phuc", required = true)
			@PathVariable("gc-code") String code,
			@ApiParam(value = "Code phân loại môn thể thao", defaultValue = "trang-phuc_bong-da, trang-phuc_bong-chuyen")
			@RequestParam(value = "cate", required = false) List<String> categoryCodeList,
			@ApiParam(value = "Phân loại màu sắc", defaultValue = "white,red")
			@RequestParam(value = "color", required = false) List<String> colorList,
			@ApiParam(value = "Phân loại khoảng giá", defaultValue = "200000")
			@RequestParam(value = "price", required = false) Integer price){
		if(categoryCodeList == null && colorList == null && price == null)
			return itemService.findItemByGeneralCategory(code);
		else
			return itemService.filterItem(code, categoryCodeList, colorList, price);
	}

	@ApiOperation(value = "Tìm theo tên sản phẩm")
	@GetMapping("/search")
	public List<ItemDto> searchItemByName(@ApiParam(value = "Tên sản phẩm") @RequestParam(name = "query") String name) {
		return itemService.searchByName(name);
	}

	@ApiOperation(value = "Tìm sản phẩm theo id")
	@GetMapping("/{id}")
	public ItemDto viewItemById(@PathVariable("id") Integer id){
		return itemService.findById(id);
	}

	@ApiOperation(value = "Danh sách sản phẩm mới nhất trong vòng 1 tuần")
	@GetMapping("/latest")
	public List<ItemDto> getLatestItemInWeek()
	{
		return itemService.findLatestItemInWeek();
	}
}
