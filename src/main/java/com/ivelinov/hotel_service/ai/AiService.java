package com.ivelinov.hotel_service.ai;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import com.ivelinov.hotel_service.ai.dto.AIAssistantResponse;
import com.ivelinov.hotel_service.ai.dto.MessageRequest;
import com.ivelinov.hotel_service.ai.dto.UserResponse;
import com.ivelinov.hotel_service.models.entity.ChatMessage;
import com.ivelinov.hotel_service.models.entity.Session;
import com.ivelinov.hotel_service.models.enums.MessageType;
import com.ivelinov.hotel_service.models.repositories.MessageRepository;
import com.ivelinov.hotel_service.models.repositories.SessionRepository;

@Service
public class AiService {
    private final OpenAIService openAIService;
    private final SessionRepository sessionRepository;
    private final MessageRepository messageRepository;

    public AiService(OpenAIService openAIService, SessionRepository sessionRepository,
            MessageRepository messageRepository) {
        this.openAIService = openAIService;
        this.sessionRepository = sessionRepository;
        this.messageRepository = messageRepository;
    }

    public UserResponse getAiResponse(MessageRequest messageRequest) throws BadRequestException {
        String message = messageRequest.message();
        String context = messageRequest.context() != null ? messageRequest.context().toString() : "";

        // We should be able to get the rules from the database and not from here
        // TODO: Implement the logic to get the rules from the database after we have
        // the admin panel
        String rules = this.getRules();

        // TODO: Put them on another thread
        Session session = findOrCreateSession(messageRequest);

        createMessage(message, session, MessageType.USER, 0L);

        LocalDateTime start = LocalDateTime.now();

        AIAssistantResponse response = this.openAIService.chatCompletion(message, rules, context);
        String aiResponse = response.choices().get(0).message().content();
        Long responseTimeMs = Duration.between(start, LocalDateTime.now()).toMillis();
        createMessage(aiResponse, session, MessageType.AI, responseTimeMs);
        return new UserResponse(aiResponse, session.getSessionId());
    }

    private void createMessage(String message, Session createdSession, MessageType messageType, Long responseTimeMs) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSession(createdSession);
        chatMessage.setMessage(message);
        chatMessage.setMessageType(messageType);
        chatMessage.setResponseTimeMs(responseTimeMs);
        chatMessage.setCreatedAt(LocalDateTime.now());
        this.messageRepository.createMessage(chatMessage);
    }

    private Session findOrCreateSession(MessageRequest messageRequest) {
        if(messageRequest.sessionId() == null) {
            return this.createNewSession(messageRequest);
        }
        Session session = this.sessionRepository.findBySessionId(messageRequest.sessionId());
        if (session == null) {
            return this.createNewSession(messageRequest);
        }
        return session;
    }

    private Session createNewSession(MessageRequest messageRequest) {
        Session session = new Session();
        session.setSessionId(UUID.randomUUID().toString());
        session.setIsActive(true);
        session.setCreatedAt(LocalDateTime.now());
        session.setUpdatedAt(LocalDateTime.now());
        Session createdSession = this.sessionRepository.createSession(session);
        return createdSession;
    }

    private String getRules() {
        return """
                # Хотел "Слънчев Бряг"

                ## Адрес
                ул. Морска 15, гр. Варна, България

                ## Работно време на рецепцията
                24/7 – винаги на разположение

                ## WiFi
                - Име на мрежата: SunnyBeachGuest
                - Парола: Sunshine2025!

                ## Паркинг
                - Безплатен паркинг за гости на хотела
                - Охраняем паркинг, работно време: 7:00 – 23:00

                ## Транспортни връзки
                - Автобусна спирка "Морска градина" – 200 метра от хотела
                - Метростанция "Център" – на 10 минути с такси

                ## Услуги и удобства
                - Закрит басейн с отопление
                - Фитнес зала, отворена от 6:00 до 22:00
                - Ресторант с българска и международна кухня (работи от 7:00 до 23:00)
                - Бар на плажа с работно време от 10:00 до 22:00
                - СПА център с масажи и сауна (работно време 9:00 – 20:00)
                - Детски клуб за деца от 4 до 12 години, работи от 9:00 до 18:00

                ## Контакти
                - Телефон рецепция: +359 52 123 456
                - Имейл: info@sunnybeachhotel.bg

                ## Допълнителна информация
                - Резервации за екскурзии и трансфери могат да се направят на рецепцията
                - Хотелът предлага безплатен трансфер до летището при резервация поне 48 часа предварително
                - В хотела е забранено пушенето в закритите помещения
                - Молим гостите да спазват тишина след 22:00 часа

                ## Важни съобщения
                - В момента има временни проблеми с топлата вода в блок B. Работи се по отстраняване на проблема и се извиняваме за неудобството.
                - Закритият басейн ще бъде затворен за профилактика от 15 до 20 август.

                ## Меню - Напитки и Храни

                ### Бира
                - Heineken – 0.5 л – 5.00 лв (алергени: съдържа малц)
                - Zagorka – 0.5 л – 4.50 лв (алергени: съдържа малц)
                - Budweiser – 0.5 л – 5.50 лв (алергени: съдържа малц)
                - Stella Artois – 0.33 л – 4.80 лв (алергени: съдържа малц)

                ### Безалкохолни напитки
                - Coca-Cola – 0.33 л – 3.00 лв
                - Минерална вода – 0.5 л – 2.50 лв
                - Прясно изцеден портокалов сок – 0.3 л – 4.50 лв

                ### Храни
                - Пица Маргарита – 12.00 лв (алергени: глутен, млечни продукти)
                - Салата Цезар – 10.00 лв (алергени: яйца, млечни продукти, риба)
                - Гръцка салата – 9.00 лв (алергени: млечни продукти)
                - Пилешки шишчета с гарнитура – 14.00 лв (алергени: няма)
                - Свински пържоли с гарнитура от печен картоф и зеленчуци – 16.00 лв (алергени: няма)
                                """;
    }
}
