package br.com.alura.forum.model

import java.time.LocalDateTime

class Question (
    var id: Long? = null,
    val title: String,
    val message: String,
    val course: Course,
    val author: User,
    val status: PostStatus = PostStatus.NOT_ANSWERED,
    val answers: List<Resposta> = ArrayList(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
)