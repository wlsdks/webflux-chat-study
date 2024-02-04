package com.example.chat.service

import com.example.chat.controller.dto.ChatMessage
import com.example.chat.repository.ChatMessageRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.Duration

@Service
class ChatService(
        private val chatMessageRepository: ChatMessageRepository
) {

    /**
     * 메시지 조회
     * Flux 타입을 사용함으로써 클라이언트에게 데이터를 스트림 형태로 지속적으로 전송할 수 있게 되어,
     * 채팅 애플리케이션에서 실시간 메시지 전송이 가능해진다.
     */
    fun streamChatMessages(chatRoomId: String): Flux<ChatMessage> {
        return chatMessageRepository.findMessagesByChatRoomId(chatRoomId)
                .delayElements(Duration.ofMillis(100)) // java.time.Duration 사용
    }

    /**
     * 메시지 전송
     */
    suspend fun postMessage(chatMessage: ChatMessage) {
        chatMessageRepository.save(chatMessage)
    }
}