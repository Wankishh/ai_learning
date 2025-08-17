package com.ivelinov.hotel_service.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OpenAIRequest(
        String model,
        List<Message> messages,
        @JsonProperty("max_tokens") Integer maxTokens,
        Double temperature
) {
    
    public record Message(
            String role,
            String content
    ) {}
}
