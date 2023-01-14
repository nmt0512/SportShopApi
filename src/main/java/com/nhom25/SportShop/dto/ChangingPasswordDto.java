package com.nhom25.SportShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangingPasswordDto {
    private String oldPassword;
    private String newPassword;
}
