package com.nhom25.SportShop.controller;

import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.response.ResponseData;
import com.nhom25.SportShop.response.ResponseUtils;
import com.nhom25.SportShop.service.ItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/home")
public class HomeItemController {
    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "Lấy thông tin tất cả sản phẩm")
    @GetMapping("/all")
    public List<ItemDto> viewAllItem() {
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
            @RequestParam(value = "price", required = false) Integer price,
            @ApiParam(value = "Phân loại size", defaultValue = "M,L")
            @RequestParam(value = "size", required = false) List<String> sizeList) {
        if (categoryCodeList == null && colorList == null && price == null && sizeList == null)
            return itemService.findItemByGeneralCategory(code);
        else
            return itemService.filterItem(code, categoryCodeList, colorList, sizeList, price);
    }

    @ApiOperation(value = "Tìm theo tên sản phẩm")
    @GetMapping("/search")
    public List<ItemDto> searchItemByName(@ApiParam(value = "Tên sản phẩm") @RequestParam(name = "query") String name) {
        return itemService.searchByName(name);
    }

    @ApiOperation(value = "Tìm sản phẩm theo id")
    @GetMapping("/{id}")
    public ItemDto viewItemById(@PathVariable("id") Integer id) {
        return itemService.findById(id);
    }

    @ApiOperation(value = "Danh sách sản phẩm mới nhất trong vòng 1 tuần")
    @GetMapping("/latest")
    public List<ItemDto> getLatestItemInWeek() {
        return itemService.findLatestItemInWeek();
    }

    @ApiOperation(value = "Lấy sản phẩm theo code")
    @GetMapping("/code")
    public List<ItemDto> getItemByCode(@RequestParam("param") String code) {
        return itemService.findByCode(code);
    }

    @ApiOperation(value = "Danh sách sản phẩm phổ biến (4 sản phẩm bán chạy nhất tất cả các thời điểm)")
    @GetMapping("/popular")
    public ResponseEntity<ResponseData<List<ItemDto>>> getMostPopularItem() {
        ResponseData<List<ItemDto>> data = new ResponseData<>();
        data.setData(itemService.getMostPopularItem());
        return ResponseUtils.success(data.getData(), data.getCode(), data.getMessage());
    }

    @ApiOperation(value = "Danh sách sản phẩm bán chạy (4 sản phẩm bán chạy nhất trong tuần)")
    @GetMapping("/bestseller")
    public ResponseEntity<ResponseData<List<ItemDto>>> getBestSellerItemInWeek() {
        ResponseData<List<ItemDto>> data = new ResponseData<>();
        data.setData(itemService.getBestSellerItemInWeek());
        return ResponseUtils.success(data.getData(), data.getCode(), data.getMessage());
    }

}
