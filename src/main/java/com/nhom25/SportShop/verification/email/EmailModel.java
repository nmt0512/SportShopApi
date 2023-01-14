package com.nhom25.SportShop.verification.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailModel {
    private String recipient;
    private String subject;
    private String content;
}
