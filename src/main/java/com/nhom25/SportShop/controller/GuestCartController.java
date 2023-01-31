package com.nhom25.SportShop.controller;

import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.entity.GuestCart;
import com.nhom25.SportShop.response.ResponseUtils;
import com.nhom25.SportShop.service.GuestCartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guest/cart")
public class GuestCartController {
    @Autowired
    private GuestCartService service;

    @ApiOperation(value = "Thêm sản phẩm vào giỏ hàng khách vãng lai")
    @PostMapping
    public GuestCart addGuestCart(@RequestBody ItemDto itemDto, @RequestParam(name = "quantity") Short quantity)
    {
        return service.addGuestCart(itemDto, quantity);
    }

    @ApiOperation(value = "Lấy tất cả sản phẩm trong giỏ hàng khách vãng lai")
    @GetMapping
    public List<GuestCart> getAllGuestCart()
    {
        return service.getAllGuestCart();
    }

    @ApiOperation(value = "Xóa sản phẩm trong giỏ hàng khách vãng lai")
    @DeleteMapping
    public ResponseEntity deleteGuestCart(@RequestParam(name = "param") Integer id)
    {
        if(service.deleteGuestCartById(id))
            return ResponseUtils.success();
        else
            return ResponseUtils.error(500, "Delete failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
