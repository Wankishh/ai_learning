package com.ivelinov.hotel_service.ai;

import com.ivelinov.hotel_service.ai.dto.MessageRequest;
import com.ivelinov.hotel_service.ai.dto.UserResponse;

import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat")
public class AIController {

    private final AiService aiService;

    public AIController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/message")
    public UserResponse processMessage(@RequestBody MessageRequest request) throws BadRequestException {
        return this.aiService.getAiResponse(request);
    }
}