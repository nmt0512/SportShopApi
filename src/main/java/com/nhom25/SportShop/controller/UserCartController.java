package com.nhom25.SportShop.controller;

import com.nhom25.SportShop.dto.BillDetail;
import com.nhom25.SportShop.dto.CartDto;
import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.dto.PaymentCartDto;
import com.nhom25.SportShop.entity.Cart;
import com.nhom25.SportShop.response.ResponseData;
import com.nhom25.SportShop.response.ResponseUtils;
import com.nhom25.SportShop.service.BillService;
import com.nhom25.SportShop.service.CartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/cart")
public class UserCartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private BillService billService;

    @ApiOperation(value = "Lấy tất cả sản phẩm trong giỏ hàng")
    @GetMapping
    public List<CartDto> viewCart() {
        return cartService.getAllItemInCart();
    }

    @ApiOperation(value = "Chỉnh sửa sản phẩm giỏ hàng")
    @PostMapping("/update")
    public Cart updateCartItem(@RequestBody CartDto cartDto) {
        return cartService.updateCart(cartDto);
    }

    @ApiOperation(value = "Xóa sản phẩm trong giỏ hàng")
    @PostMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> deleteCartItem(@PathVariable("id") Integer cartId) {
        cartService.deleteCartItem(cartId);
        return ResponseUtils.success();
    }

    @ApiOperation(value = "Thêm sản phẩm vào giỏ hàng")
    @PostMapping
    public Cart addToCart(@RequestBody ItemDto itemDto) {
        return cartService.addToCart(itemDto);
    }

    @ApiOperation(value = "Thanh toán sản phẩm trong giỏ hàng")
    @PostMapping("/payment")
    public BillDetail paymentCart(@RequestBody PaymentCartDto paymentCartDto) {
        return cartService.paymentCart(paymentCartDto);
    }

    @ApiOperation(value = "Lấy thông tin các bill của người dùng")
    @GetMapping("/bill")
    public ResponseEntity<ResponseData<List<BillDetail>>> getAllUserBill() {
        ResponseData<List<BillDetail>> data = new ResponseData<>();
        data.setData(billService.findByCurrentUsername());
        return ResponseUtils.success(data.getData(), data.getCode(), data.getMessage());
    }
}
