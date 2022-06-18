package com.splanes.commons.user_auth.domain.model.credentials

import com.splanes.commons.user_auth.domain.model.user.UserData
import java.util.Date

data class Credentials(
	val user: UserData,
	val password: SecurePassword,
	val expiresOn: Date,
	val refreshedCount: Int = 0,
	val role:
) {
	sealed class Password(open val value: String)
	data class PlainPassword(override val value: String) : Password(value)
	data class SecurePassword(override val value: String) : Password(value)
}

fun Credentials.hasExpired(): Boolean = expiresOn.before(Date())