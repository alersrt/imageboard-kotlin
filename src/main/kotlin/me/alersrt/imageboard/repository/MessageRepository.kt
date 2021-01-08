package me.alersrt.imageboard.repository

import me.alersrt.imageboard.model.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : JpaRepository<Message, Long> {
}