package com.example.vaadin_dialog_filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class Settings {
    @Value("${api.admin:url}")
    private String adminApiUrl;
    
    @Value("${api.table.action:action}")
    private String tableActionApiUrl;
    
    @Value("${token.id:123abc}")
    private String tokenId;
}
