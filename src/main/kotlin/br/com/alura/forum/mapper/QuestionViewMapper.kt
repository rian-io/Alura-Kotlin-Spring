package br.com.alura.forum.mapper

import br.com.alura.forum.dto.QuestionView
import br.com.alura.forum.model.Question
import org.springframework.stereotype.Component

@Component
class QuestionViewMapper: Mapper<Question, QuestionView> {
    override fun map(t: Question): QuestionView {
        return QuestionView(
            id = t.id,
            title = t.title,
            message = t.message,
            status = t.status,
            createdAt = t.createdAt
        )
    }
}