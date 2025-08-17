package com.ivelinov.hotel_service.ai;

import com.ivelinov.hotel_service.ai.dto.AIAssistantResponse;
import com.ivelinov.hotel_service.ai.dto.OpenAIRequest;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAIService {

    private final RestTemplate webClient;

    @Value("${openai.api.key:}")
    private String apiKey;
    
    @Value("${openai.api.url:}")
    private String openAiUrl;

    public OpenAIService() {
        this.webClient = new RestTemplate();
    }

    public AIAssistantResponse chatCompletion(String message, String rules, String context) throws BadRequestException {
        OpenAIRequest.Message systemMessage = new OpenAIRequest.Message("system", this.getSystemRules(rules, context));
        OpenAIRequest.Message userMessage = new OpenAIRequest.Message("user", message);
        Optional<AIAssistantResponse> response = this.getAiResponse(systemMessage, userMessage);
        return response.orElseThrow(() -> new BadRequestException("Failed to get AI response"));
    }

    private Optional<AIAssistantResponse> getAiResponse(OpenAIRequest.Message system, OpenAIRequest.Message user) {
        OpenAIRequest openAIRequest = new OpenAIRequest(
                "gpt-4o-mini",
                List.of(system, user),
                300,
                0.7);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<OpenAIRequest> entity = new HttpEntity<>(openAIRequest, headers);
            AIAssistantResponse response = this.webClient.postForObject(openAiUrl,
                    entity, AIAssistantResponse.class);
            return Optional.of(response);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private String getSystemRules(String rules, String context) {
        return """
                You are a virtual hotel staff member who always acts professionally, politely, and efficiently.
                You will receive context containing up-to-date hotel information (services, availability, prices, issues, contacts, menu, etc.).
                Use only the information from this context to respond. If there is no information for a specific request, politely inform the guest that you will forward their request to a real staff member.

                Rules:
                %s
                """
                .formatted(rules, context);
    }
}