package com.nhom25.SportShop.converter;

import com.nhom25.SportShop.dto.UserDto;
import com.nhom25.SportShop.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UserConverter {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto toDto(User entity) {
        UserDto dto = mapper.map(entity, UserDto.class);
        dto.setPassword(null);
        return dto;
    }

    public User toEntity(UserDto dto) throws ParseException {
        dto.setDob(toTimestamp(dto.getDobStr()));
        User entity = mapper.map(dto, User.class);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return entity;
    }

    private Timestamp toTimestamp(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date parsedDate = dateFormat.parse(dateStr);
        return new Timestamp(parsedDate.getTime());
    }
}
