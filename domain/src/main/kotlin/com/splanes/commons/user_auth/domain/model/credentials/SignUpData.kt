package com.splanes.commons.user_auth.domain.model.credentials

data class SignUpData(
	val email: String,
	val password: String,
	val firstName: String,
	val lastName: String,
	val alias: String,
)
