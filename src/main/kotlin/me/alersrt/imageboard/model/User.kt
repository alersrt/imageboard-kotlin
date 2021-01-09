package me.alersrt.imageboard.model

import javax.persistence.Column
import javax.persistence.Entity

@Entity
class User(
    @Column(unique = true, nullable = false)
    var username: String,

    @Column(unique = true, nullable = false)
    var email: String,

    @Column(nullable = false)
    var credentials: String
) : BaseEntity<Long>()