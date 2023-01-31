package com.nhom25.SportShop.controller;

import com.nhom25.SportShop.dto.BillDetail;
import com.nhom25.SportShop.dto.CartDto;
import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.dto.PaymentCartDto;
import com.nhom25.SportShop.entity.Cart;
import com.nhom25.SportShop.response.ResponseUtils;
import com.nhom25.SportShop.service.CartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class UserCartController {
    @Autowired
    private CartService cartService;

    @ApiOperation(value = "Lấy tất cả sản phẩm trong giỏ hàng")
    @GetMapping
    public List<CartDto> viewCart() {
        return cartService.getAllItemInCart();
    }

//    @ApiOperation(value = "Chỉnh sửa sản phẩm giỏ hàng")
//    @PutMapping
//    public Cart updateCartItem(@RequestBody Cart cart) {
//        return cartService.updateCartItem(cart);
//    }

    @ApiOperation(value = "Xóa sản phẩm trong giỏ hàng")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCartItem(@PathVariable("id") Integer cartId) {
        cartService.deleteCartItem(cartId);
        return ResponseUtils.success();
    }

    @ApiOperation(value = "Thêm sản phẩm vào giỏ hàng")
    @PostMapping
    public Cart addToCart(@RequestBody ItemDto itemDto, @RequestParam(name = "quantity") Short quantity)
    {
        return cartService.addToCart(itemDto, quantity);
    }

    @ApiOperation(value = "Thanh toán sản phẩm trong giỏ hàng")
    @PutMapping("/payment")
    public BillDetail paymentCart(@RequestBody PaymentCartDto paymentCartDto) {
        //test
        return cartService.paymentCart(paymentCartDto);
    }
}
