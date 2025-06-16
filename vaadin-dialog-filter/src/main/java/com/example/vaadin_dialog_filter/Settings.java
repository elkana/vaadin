package com.example.vaadin_dialog_filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class Settings {
    @Value("${jamaibase.api.admin}")
    private String adminApiUrl;
    
    @Value("${jamaibase.api.table.action}")
    private String tableActionApiUrl;
    
    @Value("${jamaibase.api.llm}")
    private String llmApiUrl;
    
    @Value("${organization.id}")
    private String organizationId;
    
    @Value("${token.id}")
    private String tokenId;
}
