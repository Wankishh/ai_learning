package com.ivelinov.hotel_service.models.repositories;

import org.springframework.stereotype.Repository;

import com.ivelinov.hotel_service.models.entity.ChatMessage;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class MessageRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public MessageRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public ChatMessage createMessage(ChatMessage message) {
        this.entityManager.persist(message);
        return message;
    }
}
