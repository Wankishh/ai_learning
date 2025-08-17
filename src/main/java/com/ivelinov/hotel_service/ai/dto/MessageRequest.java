package com.ivelinov.hotel_service.ai.dto;


public record MessageRequest (
        String message,
        Object context,
        String sessionId,
        String guestId
) {
} 