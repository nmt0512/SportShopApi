package com.nhom25.SportShop.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhom25.SportShop.dto.translate.DataResponse;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.stereotype.Component;

@Component
public class TranslateUtil {
    private String translate(String sourceKey, Boolean engToVieFlag) throws JsonProcessingException {
        String target = "en";
        String source = "vi";
        if(engToVieFlag)
        {
            target = "vi";
            source = "en";
        }
        HttpResponse<String> response = Unirest.post("https://google-translate1.p.rapidapi.com/language/translate/v2")
                .header("content-type", "application/x-www-form-urlencoded")
                .header("Accept-Encoding", "application/gzip")
                .header("X-RapidAPI-Key", "6d6a32d5f1msh5091a9fd0cee2c0p1a5c3ejsn6ce0b1de8f8f")
                .header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
                .body("q="+ sourceKey +"&target="+ target +"&source="+ source)
                .asString();
        ObjectMapper mapper = new ObjectMapper();
        DataResponse dataResponse = mapper.readValue(response.getBody(), DataResponse.class);
        return dataResponse.getData().getTranslations().get(0).getTranslatedText();
    }

    public String translateVieToEng(String sourceKey) throws JsonProcessingException {
        return translate(sourceKey, false);
    }

    public String translateEngToVie(String sourceKey) throws JsonProcessingException {
        return translate(sourceKey, true);
    }
}
