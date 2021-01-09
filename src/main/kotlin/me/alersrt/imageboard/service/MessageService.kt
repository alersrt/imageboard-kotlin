package me.alersrt.imageboard.service

import me.alersrt.imageboard.model.Message
import me.alersrt.imageboard.model.fromMultipartFile
import me.alersrt.imageboard.repository.MessageRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

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
        return messageRepository.save(
            Message(
                title = title,
                text = text,
                attachments = attachments.map { a -> fromMultipartFile(a) }.toMutableSet()
            )
        )
    }
}