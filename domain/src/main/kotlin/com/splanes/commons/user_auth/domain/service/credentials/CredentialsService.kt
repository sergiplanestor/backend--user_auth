package com.splanes.commons.user_auth.domain.service.credentials

import com.splanes.commons.user_auth.domain.model.credentials.SignUpData
import com.splanes.commons.user_auth.domain.model.user.UserData
import org.springframework.stereotype.Service

@Service
interface CredentialsService {
	suspend fun signUp(data: SignUpData): Boolean
	// suspend fun signIn()
}