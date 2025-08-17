package com.ivelinov.hotel_service.models.repositories;

import org.springframework.stereotype.Repository;

import com.ivelinov.hotel_service.models.entity.Session;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class SessionRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public SessionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Session createSession(Session session) {
        this.entityManager.persist(session);
        return session;
    }

    public Session findBySessionId(String sessionId) {
        return this.entityManager.createQuery("SELECT s FROM Session s WHERE s.sessionId = :sessionId", Session.class)
                .setParameter("sessionId", sessionId)
                .getResultList().stream().findFirst().orElse(null);
    }
}
