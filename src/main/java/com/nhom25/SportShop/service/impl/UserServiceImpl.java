package com.nhom25.SportShop.service.impl;

import com.nhom25.SportShop.converter.UserConverter;
import com.nhom25.SportShop.dto.ChangingPasswordDto;
import com.nhom25.SportShop.dto.UserDto;
import com.nhom25.SportShop.entity.User;
import com.nhom25.SportShop.repository.UserRepository;
import com.nhom25.SportShop.security.UserDetailsServiceImpl;
import com.nhom25.SportShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserConverter converter;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Boolean isEmailExisted(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public UserDto addUser(UserDto dto) {
        User userEntity = converter.toEntity(dto);
        userEntity.setRole(false);
        return converter.toDto(userRepo.save(userEntity));
    }

    @Override
    public UserDto updateUser(UserDto dto) {
        return null;
    }

    @Override
    public Boolean isUsernameExisted(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public UserDto updateUserPassword(ChangingPasswordDto changingPasswordDto) {
        String currentUsername = userDetailsService.getCurrentUsername();
        String currentPassword = userDetailsService.loadUserByUsername(currentUsername).getPassword();
        if(passwordEncoder.matches(changingPasswordDto.getOldPassword(), currentPassword))
        {
            String encodedNewPassword = passwordEncoder.encode(changingPasswordDto.getNewPassword());
            userRepo.updateUserPassword(encodedNewPassword, currentUsername);
            User userEntity = userRepo.findByUsername(currentUsername);
            return converter.toDto(userEntity);
        }
        return null;
    }
}
