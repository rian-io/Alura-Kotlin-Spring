package br.com.alura.forum.service

import br.com.alura.forum.dto.NewQuestionForm
import br.com.alura.forum.dto.QuestionView
import br.com.alura.forum.dto.UpdateQuestionForm
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.QuestionFormMapper
import br.com.alura.forum.mapper.QuestionViewMapper
import br.com.alura.forum.model.Question
import org.springframework.stereotype.Service

@Service
class QuestionService(
    private var questions: List<Question> = ArrayList(),
    private val questionViewMapper: QuestionViewMapper,
    private val questionFormMapper: QuestionFormMapper
) {
    fun list(): List<QuestionView> {
        return questions.map { q -> questionViewMapper.map(q) }
    }

    fun getById(id: Long): QuestionView {
        val question = questions.stream().filter { q -> q.id == id }.findFirst().orElseThrow { NotFoundException() }
        return questionViewMapper.map(question)
    }

    fun register(form: NewQuestionForm): QuestionView {
        val newQuestion: Question = questionFormMapper.map(form)
        newQuestion.id = questions.size + 1.toLong()

        questions = questions.plus(newQuestion)

        return questionViewMapper.map(newQuestion)
    }

    fun update(form: UpdateQuestionForm): QuestionView {
        val question: Question =
            questions.stream().filter { q -> q.id == form.id }.findFirst().orElseThrow { NotFoundException() }
        val updatedQuestion = Question(
            id = question.id,
            title = form.title,
            message = form.message,
            author = question.author,
            course = question.course,
            answers = question.answers,
            status = question.status,
            createdAt = question.createdAt
        )
        questions = questions.minus(question).plus(updatedQuestion)

        return questionViewMapper.map(updatedQuestion)
    }

    fun delete(id: Long) {
        val question = questions.stream().filter { q -> q.id == id }.findFirst().orElseThrow { NotFoundException() }
        questions = questions.minus(question)
    }
}