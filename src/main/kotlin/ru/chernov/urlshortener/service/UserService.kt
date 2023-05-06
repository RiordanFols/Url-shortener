package ru.chernov.urlshortener.service

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import ru.chernov.urlshortener.dto.user.UserRegisterRequest
import ru.chernov.urlshortener.entity.user.User
import ru.chernov.urlshortener.exception.link.LinkNotFoundException
import ru.chernov.urlshortener.exception.user.UserNotFoundException
import ru.chernov.urlshortener.repository.UserRepository
import java.util.*


@Service
class UserService(private val userRepository: UserRepository) : UserDetailsService {
    private val logger: Logger = LogManager.getLogger(UserService::class);


    override fun loadUserByUsername(username: String): User {
        return userRepository.findByUsername(username) ?: run {
            logger.error("User username=[{}] not found.", username)
            throw LinkNotFoundException()
        }
    }


    fun findById(id: Long): User {
        return userRepository.findById(id).orElseThrow {
            logger.error("User [{}] not found.", id)
            throw UserNotFoundException()
        }
    }


    fun register(registerRequest: UserRegisterRequest) {
        // TODO: шифрование паролей
        val user = User()
        user.username = registerRequest.username
        user.password = registerRequest.password
        user.token = UUID.randomUUID()
        user.active = true
        userRepository.save(user)
    }

}