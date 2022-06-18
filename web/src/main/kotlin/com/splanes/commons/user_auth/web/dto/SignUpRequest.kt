package com.splanes.commons.user_auth.web.dto

import com.fasterxml.jackson.annotation.JsonAlias
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class SignUpRequest(
	@field:JsonAlias("email")
	@field:Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
	@field:NotBlank
	val email: String,

	@field:JsonAlias("pwd")
	@field:Pattern(regexp = "^(?=^.{8,}\$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*\$")
	@field:NotBlank
	val password: String,

	@field:JsonAlias("first_name")
	@field:NotBlank
	val firstName: String,

	@field:JsonAlias("last_name")
	val lastName: String?,

	@field:JsonAlias("user_alias")
	val alias: String?,
)
