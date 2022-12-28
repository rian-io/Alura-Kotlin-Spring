package br.com.alura.forum.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Question (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String,
    var message: String,
    @ManyToOne
    val course: Course,
    @ManyToOne
    val author: User,
    @Enumerated(value = EnumType.STRING)
    val status: PostStatus = PostStatus.NOT_ANSWERED,
    @OneToMany(mappedBy = "question")
    val answers: List<Answer> = ArrayList(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
)