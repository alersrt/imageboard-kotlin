package me.alersrt.imageboard.service

import me.alersrt.imageboard.model.Attachment
import me.alersrt.imageboard.model.Message
import me.alersrt.imageboard.repository.MessageRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class MessageService(private val messageRepository: MessageRepository) {

    /**
     * Get list of all stored messages.
     */
    fun getAll(): List<Message> = messageRepository.findAll()

    /**
     * Add the new message to storage.
     */
    fun add(title: String?, text: String?, attachments: List<MultipartFile>): Message {
        val attachments = attachments.map { a ->
            Attachment(
                blob = a.bytes,
                filename = UUID.randomUUID().toString(),
                mime = a.contentType ?: "*/*"
            )
        }
        return messageRepository.save(
            Message(
                title = title,
                text = text,
                attachments = attachments.toMutableSet()
            )
        )
    }
}