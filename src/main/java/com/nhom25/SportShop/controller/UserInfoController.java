package com.nhom25.SportShop.controller;

import com.nhom25.SportShop.dto.ChangingPasswordDto;
import com.nhom25.SportShop.dto.UserDto;
import com.nhom25.SportShop.response.ResponseData;
import com.nhom25.SportShop.response.ResponseUtils;
import com.nhom25.SportShop.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class UserInfoController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Xác thực username hay email đã tồn tại hay chưa")
    @PostMapping("/user/validate")
    public ResponseEntity validateUser(@RequestBody UserDto userDto) {
        if(userService.isEmailExisted(userDto.getEmail()))
        {
            return ResponseUtils.error(400, "Email already exists",HttpStatus.BAD_REQUEST);
        }
        else if(userService.isUsernameExisted(userDto.getUsername()))
        {
            return ResponseUtils.error(400, "Username already exists",HttpStatus.BAD_REQUEST);
        }
        return ResponseUtils.success();
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
        ResponseData<UserDto> data = new ResponseData<>();
        data.setData(userDto);
        return ResponseUtils.success(data);
    }

    @ApiOperation(value = "Đăng ký")
    @PostMapping("/user")
    public ResponseEntity registerUser(@RequestBody UserDto userDto) {
        UserDto user = userService.addUser(userDto);
        ResponseData<UserDto> data = new ResponseData<>();
        data.setData(user);
        return ResponseUtils.success(data);
    }
}
