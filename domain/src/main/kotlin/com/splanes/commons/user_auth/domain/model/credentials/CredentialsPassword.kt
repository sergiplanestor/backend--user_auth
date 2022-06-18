package com.splanes.commons.user_auth.domain.model.credentials

sealed class CredentialsPassword(open val value: String) {
	data class Plain(override val value: String): CredentialsPassword(value)
	data class Secure(override val value: String): CredentialsPassword(value)
}
