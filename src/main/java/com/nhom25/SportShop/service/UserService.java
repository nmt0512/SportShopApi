package com.nhom25.SportShop.service;

import com.nhom25.SportShop.dto.ChangingPasswordDto;
import com.nhom25.SportShop.dto.UserDto;

import java.text.ParseException;

public interface UserService {
    Boolean isEmailExisted(String email);

    UserDto addUser(UserDto dto) throws ParseException;

    UserDto updateUser(UserDto dto);

    Boolean isUsernameExisted(String username);

    UserDto updateUserPassword(ChangingPasswordDto changingPasswordDto);
}
