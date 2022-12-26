package br.com.alura.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class UpdateQuestionForm(
    @field:NotNull val id: Long,
    @field:NotEmpty val title: String,
    @field:NotEmpty val message: String
)
