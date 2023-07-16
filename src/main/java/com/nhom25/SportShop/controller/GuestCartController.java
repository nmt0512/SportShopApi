package com.nhom25.SportShop.controller;

import com.nhom25.SportShop.entity.GuestCart;
import com.nhom25.SportShop.response.ResponseData;
import com.nhom25.SportShop.response.ResponseUtils;
import com.nhom25.SportShop.service.GuestCartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/guest/cart")
public class GuestCartController {
    @Autowired
    private GuestCartService guestCartService;

    @ApiOperation(value = "Thêm sản phẩm vào giỏ hàng của khách vãng lai")
    @PostMapping("/add")
    public ResponseEntity<ResponseData<GuestCart>> addToGuestCart(@RequestBody GuestCart guestCart, HttpServletRequest request) {
        ResponseData<GuestCart> data = guestCartService.addGuestCart(request.getSession().getId(), guestCart);
        return ResponseUtils.success(data.getData(), data.getCode(), data.getMessage());
    }

    @ApiOperation(value = "Chỉnh sửa sản phẩm trong giỏ hàng của khách vãng lai")
    @PostMapping("/update")
    public ResponseEntity<ResponseData<GuestCart>> updateGuestCart(@RequestBody GuestCart guestCart, HttpServletRequest request) {
        ResponseData<GuestCart> data = guestCartService.updateGuestCart(request.getSession().getId(), guestCart);
        return ResponseUtils.success(data.getData(), data.getCode(), data.getMessage());
    }

    @ApiOperation(value = "Lấy thông tin tất cả sản phẩm trong giỏ hàng khách vãng lai")
    @GetMapping
    public ResponseEntity<ResponseData<List<GuestCart>>> getAllGuestCart(HttpServletRequest request) {
        ResponseData<List<GuestCart>> data = guestCartService.getAllGuestCart(request.getSession().getId());
        return ResponseUtils.success(data.getData(), data.getCode(), data.getMessage());
    }

    @ApiOperation(value = "Xóa các sản phẩm trong giỏ hàng khách vãng lai")
    @PostMapping("/delete")
    public ResponseEntity<ResponseData<Void>> deleteGuestCart(@RequestParam("id") List<Integer> idList, HttpServletRequest request) {
        guestCartService.deleteGuestCart(request.getSession().getId(), idList);
        return ResponseUtils.success();
    }

}
