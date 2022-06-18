package com.splanes.commons.user_auth.domain.model.credentials

import com.splanes.commons.user_auth.domain.model.user.UserData

data class CredentialsRequestBO(
	val user: UserData,
	val pwd: CredentialsPassword
)
