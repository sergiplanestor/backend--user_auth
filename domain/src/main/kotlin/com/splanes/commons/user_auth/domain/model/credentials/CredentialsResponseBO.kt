package com.splanes.commons.user_auth.domain.model.credentials

import com.splanes.commons.user_auth.domain.model.user.UserData
import java.util.Date

data class CredentialsResponseBO(
	val user: UserData,
	val pwd: CredentialsPassword,
	val expiration: Date,
	val refreshedTimes: Int
) {
	val isExpired: Boolean get() = expiration.after(Date())
}
