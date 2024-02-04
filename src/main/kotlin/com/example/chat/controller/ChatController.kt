package com.example.chat.controller

import com.example.chat.controller.dto.ChatMessage
import com.example.chat.service.ChatService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/chat")
class ChatController(
        // 생성자 주입
        private val chatService: ChatService
) {

    /**
     * MediaType.TEXT_EVENT_STREAM_VALUE를 사용함으로써,
     * 스프링은 클라이언트에게 데이터를 스트림으로 전송할 준비가 되었음을 알리고,
     * 연결을 열어 두면서 새로운 데이터가 생길 때마다 이를 클라이언트로 푸시하게 된다.
     */
    @GetMapping("/stream/{chatRoomId}", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun streamChatMessages(@PathVariable chatRoomId: String): Flux<ChatMessage> {
        return chatService.streamChatMessages(chatRoomId)
    }

    @PostMapping
    suspend fun postMessage(@RequestBody chatMessage: ChatMessage) {
        chatService.postMessage(chatMessage)
    }

}