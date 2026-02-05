package com.demo.project.user

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()

    fun create(user: User): User {
        return userRepository.save(user)
    }
}