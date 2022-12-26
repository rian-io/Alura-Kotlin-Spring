package br.com.alura.forum.mapper

import br.com.alura.forum.dto.NewQuestionForm
import br.com.alura.forum.model.Question
import br.com.alura.forum.service.CourseService
import br.com.alura.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class QuestionFormMapper(
    private val courseService: CourseService,
    private val userService: UserService,
) : Mapper<NewQuestionForm, Question> {
    override fun map(t: NewQuestionForm): Question {
        return Question(
            title = t.title,
            message = t.message,
            course = courseService.getById(t.idCourse),
            author = userService.getById(t.idAuthor)
        )
    }

}
