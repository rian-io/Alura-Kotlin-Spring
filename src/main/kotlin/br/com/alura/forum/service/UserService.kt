package br.com.alura.forum.service

import br.com.alura.forum.model.User
import br.com.alura.forum.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository
) {
    fun getById(id: Long): User {
        return repository.findById(id).get()
    }
}
