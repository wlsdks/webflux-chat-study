package com.example.chat.controller.dto

import java.time.Instant

/**
 * 채팅 dto객체
 */
data class ChatMessage(
        val id: String? = null,
        val chatRoomId: String,
        val sender: String,
        val message: String,
        val timeStamp: Instant = Instant.now()
)