package me.alersrt.imageboard.model

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.validation.constraints.NotNull

@Entity
open class Attachment(
        @NotNull
        @Column(unique = true, nullable = false, updatable = false)
        var filename: String,

        @NotNull
        @Column(nullable = false)
        var mime: String,

        @NotNull
        @Column(nullable = false)
        var blob: ByteArray,

        @LazyCollection(LazyCollectionOption.EXTRA)
        @ManyToMany(mappedBy = "attachments") val messages: MutableSet<Message> = mutableSetOf(),
) : BaseEntity<Long>()