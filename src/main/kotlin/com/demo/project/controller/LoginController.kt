package com.demo.project.controller

import com.demo.project.dto.LoginRequestDTO
import com.demo.project.dto.ResponseDTO
import com.demo.project.infra.security.TokenService
import com.demo.project.user.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/login")
class LoginController (
    private val repository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenService: TokenService
) {
    @PostMapping
    fun login(@RequestBody loginInfo: LoginRequestDTO): ResponseEntity<ResponseDTO> {

        val user = repository.findByEmail(loginInfo.email)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found")

        if (!passwordEncoder.matches(loginInfo.password, user.password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }

        val token = tokenService.generateToken(user)
        val response = ResponseDTO(user.name, token)

        return ResponseEntity.ok(response)
    }
    @GetMapping
    fun alertLogin(): ResponseEntity<String> {
        return ResponseEntity.ok("Faça login")
    }

}