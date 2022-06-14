package com.splanes.commons.user_auth.web.api

import com.splanes.commons.user_auth.web.security.jwt.JwtSigner
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

data class User(val email: String)

data class UserCredentials(val email: String, val password: String)

@RestController
@RequestMapping("/identity")
class AuthController(private val jwtSigner: JwtSigner) {

    private val users: MutableMap<String, UserCredentials> = mutableMapOf(
        "email@example.com" to UserCredentials("email@example.com", "pw")
    )

    @PutMapping("/auth/signup")
    @ResponseStatus(HttpStatus.OK)
    fun signUp(@RequestBody user: UserCredentials): Mono<ResponseEntity<Void>> {
        users[user.email] = user

        return Mono.just(ResponseEntity.noContent().build())
    }

    @PostMapping("/auth/login")
    fun login(@RequestBody user: UserCredentials): Mono<ResponseEntity<Void>> {
        return Mono.justOrEmpty(users[user.email])
            .filter { it.password == user.password }
            .map {
                val jwt = jwtSigner.createJwt(it.email)
                val authCookie = ResponseCookie.fromClientResponse("X-Auth", jwt)
                    .maxAge(3600)
                    .httpOnly(true)
                    .path("/")
                    .secure(false) // should be true in production
                    .build()

                ResponseEntity.noContent()
                    .header("Set-Cookie", authCookie.toString())
                    .build<Void>()
            }
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()))
    }

    /*@GetMapping
    fun getMyself(principal: Principal): Mono<ResponseEntity<User>> {
        return Mono.justOrEmpty(users[principal.name])
            .map { ResponseEntity.ok(User(it.email)) }
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()))
    }*/
}