package br.com.alura.forum.service

import br.com.alura.forum.dto.NewQuestionForm
import br.com.alura.forum.dto.QuestionView
import br.com.alura.forum.dto.UpdateQuestionForm
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.QuestionFormMapper
import br.com.alura.forum.mapper.QuestionViewMapper
import br.com.alura.forum.model.Question
import br.com.alura.forum.repository.QuestionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class QuestionService(
    private val repository: QuestionRepository,
    private val questionViewMapper: QuestionViewMapper,
    private val questionFormMapper: QuestionFormMapper
) {
    fun list(
        courseName: String?,
        pagination: Pageable
    ): Page<QuestionView> {
        val questions = if (courseName == null) {
            repository.findAll(pagination)
        } else {
            repository.findByCourseName(courseName, pagination)
        }
        return questions.map { q -> questionViewMapper.map(q) }
    }

    fun getById(id: Long): QuestionView {
        val question = repository.findById(id).orElseThrow { NotFoundException() }
        return questionViewMapper.map(question)
    }

    fun register(form: NewQuestionForm): QuestionView {
        val newQuestion: Question = questionFormMapper.map(form)
        repository.save(newQuestion)
        return questionViewMapper.map(newQuestion)
    }

    fun update(form: UpdateQuestionForm): QuestionView {
        val question: Question = repository.findById(form.id).orElseThrow { NotFoundException() }
        question.title = form.title
        question.message = form.message

        return questionViewMapper.map(question)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }
}