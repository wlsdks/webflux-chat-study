package com.example.chat.repository

import com.example.chat.controller.dto.ChatMessage
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface ChatMessageRepository : ReactiveMongoRepository<ChatMessage, String> {

    // Flux<ChatMessage>는 0개 이상의 ChatMessage 객체를 비동기적으로 스트림 형태로 반환하는 리액티브 타입이다.
    fun findMessagesByChatRoomId(chatRoomId: String): Flux<ChatMessage>

}
