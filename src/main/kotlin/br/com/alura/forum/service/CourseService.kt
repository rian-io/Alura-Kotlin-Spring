package br.com.alura.forum.service

import br.com.alura.forum.model.Course
import org.springframework.stereotype.Service

@Service
class CourseService(
    var courses: List<Course>
) {
    init {
        val course = Course(
            id = 1,
            name = "Kotlin",
            category = "Programming"
        )

        courses = listOf(course)
    }

    fun getById(id: Long): Course {
        return courses.first { c -> c.id == id }
    }
}
