package com.demo.project.controller

import com.demo.project.dto.RegisterRequestDTO
import com.demo.project.dto.ResponseDTO
import com.demo.project.infra.security.TokenService
import com.demo.project.user.User
import com.demo.project.user.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/register")
class RegisterController (
    private val repository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenService: TokenService
) {
    @PostMapping
    fun register(@RequestBody body: RegisterRequestDTO): ResponseEntity<ResponseDTO> {

        val user = repository.findByEmail(body.email)

        if (user == null) {
            val newUser = User(
                email = body.email,
                name = body.name,
                password = passwordEncoder.encode(body.password).toString(),
            )

            repository.save(newUser)

            val token = tokenService.generateToken(newUser)

            return ResponseEntity.ok(ResponseDTO(newUser.name, token))
        }

        return ResponseEntity.badRequest().build()
    }

}