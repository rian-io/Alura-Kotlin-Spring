package br.com.alura.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull


data class NewQuestionForm(
    @field:NotEmpty(message = "Title cannot be empty")
    val title: String,
    @field:NotEmpty(message = "Message cannot be empty")
    val message: String,
    @field:NotNull
    val idCourse: Long,
    @field:NotNull
    val idAuthor: Long
)
