package com.splanes.commons.user_auth.web.dto

import com.fasterxml.jackson.annotation.JsonAlias
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

sealed class SignInRequest(open val password: String) {
	data class Email(
		@field:JsonAlias("email")
		@field:Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
		@field:NotBlank
		val email: String,

		@field:JsonAlias("pwd")
		@field:Pattern(regexp = "^(?=^.{8,}\$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*\$")
		@field:NotBlank
		override val password: String
	) : SignInRequest(password)

	data class Alias(
		@field:JsonAlias("user_alias")
		@field:NotBlank
		val alias: String,

		@field:JsonAlias("pwd")
		@field:Pattern(regexp = "^(?=^.{8,}\$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*\$")
		@field:NotBlank
		override val password: String
	) : SignInRequest(password)
}
