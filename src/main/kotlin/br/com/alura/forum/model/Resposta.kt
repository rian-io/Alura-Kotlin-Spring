package br.com.alura.forum.model

import java.time.LocalDateTime

data class Resposta (
    val id: Long? = null,
    val message: String,
    val author: User,
    val question: Question,
    val isSolutions: Boolean,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
