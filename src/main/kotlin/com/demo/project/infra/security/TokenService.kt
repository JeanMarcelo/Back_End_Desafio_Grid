package com.demo.project.infra.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.demo.project.user.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset


@Service
class TokenService(
    @Value("\${api.security.token.secret}")
    private val secret: String
) {

    fun generateToken(user: User): String {
        return try {
            val algorithm = Algorithm.HMAC256(secret)

            JWT.create()
                .withIssuer("My-Api")
                .withSubject(user.email)
                .withExpiresAt(generateExpirationDate())
                .sign(algorithm)

        } catch (exception: JWTCreationException) {
            throw RuntimeException("Error while authentication")
        }
    }

    fun validateToken(token: String): String {
        try {
            val algorithm = Algorithm.HMAC256(secret)
            return JWT.require(algorithm)
                .withIssuer("My-Api")
                .build()
                .verify(token)
                .getSubject()
        } catch (ex: JWTVerificationException) {
            throw RuntimeException("Invalid token")
        }

    }

    fun generateExpirationDate(): Instant {
        return LocalDateTime.now()
            .plusHours(2)
            .toInstant(ZoneOffset.of("-03:00"))
    }
}