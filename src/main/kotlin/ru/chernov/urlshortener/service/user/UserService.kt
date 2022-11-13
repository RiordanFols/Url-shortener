package ru.chernov.urlshortener.service.user

import org.springframework.stereotype.Service
import ru.chernov.urlshortener.dto.user.UserRegisterRequest
import ru.chernov.urlshortener.entity.user.User
import ru.chernov.urlshortener.repository.user.UserRepository
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) {

    fun register(registerRequest: UserRegisterRequest) {
        val user = User()
        user.username = registerRequest.username
        // TODO: шифрование паролей
        user.password = registerRequest.password
        user.token = UUID.randomUUID().toString()
        user.active = true
        userRepository.save(user)
    }

}