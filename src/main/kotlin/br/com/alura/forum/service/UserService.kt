package br.com.alura.forum.service

import br.com.alura.forum.model.User
import org.springframework.stereotype.Service

@Service
class UserService(
    var users: List<User>
) {
    init {
        val user = User(
            id = 1,
            name = "Anna",
            email = "anna@email.com"
        )

        users = listOf(user)
    }

    fun getById(id: Long): User {
        return users.first { u -> u.id == id }
    }
}
