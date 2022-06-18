package com.splanes.commons.user_auth.domain.service.credentials.impl

import com.splanes.commons.user_auth.domain.model.credentials.SignUpData
import com.splanes.commons.user_auth.domain.repository.CredentialsRepository
import com.splanes.commons.user_auth.domain.service.credentials.CredentialsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CredentialsServiceImpl(
	@Autowired private val repository: CredentialsRepository
) : CredentialsService {

	override suspend fun signUp(data: SignUpData): Boolean {
		with(data) {

		}
		repository.
	}
}