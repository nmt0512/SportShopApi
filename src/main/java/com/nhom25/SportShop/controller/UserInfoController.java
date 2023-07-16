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

import java.text.ParseException;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/home")
public class UserInfoController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Xác thực username hay email đã tồn tại hay chưa")
    @PostMapping("/user/validate")
    public ResponseEntity<ResponseData<Void>> validateUser(@RequestBody UserDto userDto) {
        if (userService.isEmailExisted(userDto.getEmail())) {
            return ResponseUtils.error(400, "Email already exists", HttpStatus.BAD_REQUEST);
        } else if (userService.isUsernameExisted(userDto.getUsername())) {
            return ResponseUtils.error(400, "Username already exists", HttpStatus.BAD_REQUEST);
        }
        return ResponseUtils.success();
    }

    @ApiOperation(value = "Chỉnh sửa thông tin người dùng")
    @PostMapping("/user/info")
    public UserDto updateUserInfo(@RequestBody UserDto dto) {
        return userService.updateUser(dto);
    }

    @ApiOperation(value = "Đổi mật khẩu người dùng")
    @PostMapping("/user/password")
    public ResponseEntity<ResponseData<UserDto>> updateUserPassword(@RequestBody ChangingPasswordDto changingPasswordDto) {
        UserDto userDto = userService.updateUserPassword(changingPasswordDto);
        ResponseData<UserDto> data = new ResponseData<>();
        data.setData(userDto);
        return ResponseUtils.success(data.getData());
    }

    @ApiOperation(value = "Đăng ký")
    @PostMapping("/user/register")
    public ResponseEntity<ResponseData<UserDto>> registerUser(@RequestBody UserDto userDto) {
        try {
            UserDto user = userService.addUser(userDto);
            ResponseData<UserDto> data = new ResponseData<>();
            data.setData(user);
            return ResponseUtils.success(data.getData());
        } catch (ParseException e) {
            return ResponseUtils.error(400, "Wrong date format", HttpStatus.BAD_REQUEST);
        }
    }
}
