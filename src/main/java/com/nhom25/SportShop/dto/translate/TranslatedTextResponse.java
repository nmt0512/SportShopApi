package com.nhom25.SportShop.dto.translate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TranslatedTextResponse {
    @JsonProperty("translatedText")
    private String translatedText;
}
