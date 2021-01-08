package me.alersrt.imageboard.controller

import me.alersrt.imageboard.model.Message
import me.alersrt.imageboard.service.MessageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime

@RestController
@RequestMapping("/messages")
class MessageController(private val service: MessageService) {

    @GetMapping
    fun getMessages(): ResponseEntity<List<MessageDto>> = ResponseEntity.ok(service.getAll().map(::MessageDto))

    @PostMapping
    fun addMessage(@RequestBody incomingMessage: IncomingMessage): ResponseEntity<MessageDto> {
        val message = service.add(
            title = incomingMessage.title,
            text = incomingMessage.text,
            attachments = incomingMessage.attachments ?: mutableListOf()
        )
        return ResponseEntity.ok(MessageDto(message))
    }

    data class IncomingMessage(var title: String?, var text: String?, var attachments: List<MultipartFile>?)

    data class MessageDto(private val message: Message) {
        var timestamp: LocalDateTime? = message.timestamp
        var title: String? = message.title
        var text: String? = message.text
        var attachments: List<String> = message.attachments.map { it.filename }
    }
}