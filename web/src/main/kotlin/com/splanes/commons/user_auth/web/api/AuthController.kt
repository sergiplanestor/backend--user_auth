package com.splanes.commons.user_auth.web.api

import com.splanes.commons.user_auth.domain.service.credentials.CredentialsService
import com.splanes.commons.user_auth.web.dto.SignUpRequest
import com.splanes.commons.user_auth.web.security.jwt.JwtSigner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/auth")
class AuthController(
	@Autowired private val credentialsService: CredentialsService,
	private val jwtSigner: JwtSigner
) {

	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.OK)
	fun signUp(@RequestBody user: SignUpRequest): Mono<ResponseEntity<Void>> {
		credentialsService.

		return Mono.just(ResponseEntity.noContent().build())
	}

	@PostMapping("/signin")
	fun signIn(@RequestBody user: UserCredentials): Mono<ResponseEntity<Void>> {
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
}