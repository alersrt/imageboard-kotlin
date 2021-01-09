package me.alersrt.imageboard.model

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import org.springframework.util.FileCopyUtils
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.File
import java.io.InputStream
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.validation.constraints.NotNull

@Entity
class Attachment(
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

fun fromMultipartFile(multipartFile: MultipartFile): Attachment = Attachment(
    blob = multipartFile.bytes,
    filename = UUID.randomUUID().toString(),
    mime = multipartFile.contentType ?: "*/*"
)

fun toMultiPartFile(attachment: Attachment): MultipartFile = object : MultipartFile {
    override fun getInputStream(): InputStream = ByteArrayInputStream(attachment.blob)

    override fun getName(): String = attachment.filename

    override fun getOriginalFilename(): String? = attachment.filename

    override fun getContentType(): String? = attachment.mime

    override fun isEmpty(): Boolean = attachment.blob.size == 0

    override fun getSize(): Long = attachment.blob.size.toLong()

    override fun getBytes(): ByteArray = attachment.blob

    override fun transferTo(dest: File) {
        FileCopyUtils.copy(attachment.blob, dest)
    }
}