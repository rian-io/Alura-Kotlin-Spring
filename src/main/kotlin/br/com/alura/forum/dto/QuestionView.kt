package br.com.alura.forum.dto

import br.com.alura.forum.model.PostStatus
import java.time.LocalDateTime

data class QuestionView(
    val id: Long?,
    val title: String,
    val message: String,
    val status: PostStatus,
    val createdAt: LocalDateTime
)
