package com.nhom25.SportShop.controller;

import com.nhom25.SportShop.dto.ChangingPasswordDto;
import com.nhom25.SportShop.dto.ItemDto;
import com.nhom25.SportShop.dto.UserDto;
import com.nhom25.SportShop.service.ItemService;
import com.nhom25.SportShop.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private ItemService itemService;
	@Autowired
	private UserService userService;


	// Item Controller
	//
	//

	@ApiOperation(value = "Lấy thông tin tất cả sản phẩm")
	@GetMapping("/all")
	public List<ItemDto> viewAllItem(){
		return itemService.findAllItem();
	}

	@ApiOperation(value = "Lấy thông tin sản phẩm theo phân loại")
	@GetMapping("/category/{category-code}")
	public List<ItemDto> viewItemByCategory(
			@ApiParam(value = "Code phân loại sản phẩm", defaultValue = "ao-bong-da", required = true)
			@PathVariable("category-code") String code){
		return itemService.findItemByCategory(code);
	}

	@ApiOperation(value = "Lấy thông tin sản phẩm theo phân loại chung")
	@GetMapping("/general/{gc-code}")
	public List<ItemDto> viewItemByGeneralCategory(
			@ApiParam(value = "Code phân loại chung sản phẩm", defaultValue = "trang-phuc", required = true)
			@PathVariable("gc-code") String code){
		return itemService.findItemByGeneralCategory(code);
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

	//UserInformationController
	//
	//

	@ApiOperation(value = "Xác thực username hay email đã tồn tại hay chưa")
	@GetMapping("/user/validate")
	public ResponseEntity validateUser(@RequestBody UserDto userDto) {
		if(userService.isEmailExisted(userDto.getEmail()))
		{
			return ResponseEntity.status(600).body("Email already exists");
		}
		else if(userService.isUsernameExisted(userDto.getUsername()))
		{
			return ResponseEntity.status(601).body("Username already exists");
		}
			return ResponseEntity.ok("OK");
	}

	@ApiOperation(value = "Chỉnh sửa thông tin người dùng")
	@PutMapping("/user/info")
	public UserDto updateUserInfo(@RequestBody UserDto dto) {
		return userService.updateUser(dto);
	}

	@ApiOperation(value = "Đổi mật khẩu người dùng")
	@PutMapping("/user/password")
	public ResponseEntity updateUserPassword(@RequestBody ChangingPasswordDto changingPasswordDto)
	{
		UserDto userDto = userService.updateUserPassword(changingPasswordDto);
		return ResponseEntity.ok(userDto);
	}

	@ApiOperation(value = "Đăng ký")
	@PostMapping("/user")
	public ResponseEntity registerUser(@RequestBody UserDto userDto) {
		UserDto user = userService.addUser(userDto);
		return ResponseEntity.ok(user);
	}

}
