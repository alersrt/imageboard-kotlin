package me.alersrt.imageboard.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.validation.constraints.Size

@Entity
class Message(
    @Size(max = 100) var title: String? = null,

    @Size(max = 1024) var text: String? = null,

    @CreationTimestamp var timestamp: LocalDateTime? = LocalDateTime.now(),

    @LazyCollection(LazyCollectionOption.EXTRA)
    @ManyToMany @JoinTable(
        name = "message_attachment",
        joinColumns = [JoinColumn(name = "message_id")],
        inverseJoinColumns = [JoinColumn(name = "attachment_id")]
    ) var attachments: MutableSet<Attachment> = mutableSetOf()
) : BaseEntity<Long>()