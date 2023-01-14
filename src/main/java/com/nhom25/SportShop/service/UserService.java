package com.nhom25.SportShop.service;

import com.nhom25.SportShop.dto.ChangingPasswordDto;
import com.nhom25.SportShop.dto.UserDto;

public interface UserService {
    Boolean isEmailExisted(String email);
    UserDto addUser(UserDto dto);
    UserDto updateUser(UserDto dto);
    Boolean isUsernameExisted(String username);
    UserDto updateUserPassword(ChangingPasswordDto changingPasswordDto);
}
