package com.ivelinov.hotel_service.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import com.ivelinov.hotel_service.models.enums.MessageType;

import java.time.LocalDateTime;


@Entity
@Table(name = "chat_messages")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_type", nullable = false)
    private MessageType messageType;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "response_time_ms")
    private Long responseTimeMs;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
} 