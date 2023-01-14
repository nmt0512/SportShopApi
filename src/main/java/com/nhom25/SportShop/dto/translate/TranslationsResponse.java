package com.nhom25.SportShop.dto.translate;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TranslationsResponse {
    private List<TranslatedTextResponse> translations = new ArrayList<>();
}
