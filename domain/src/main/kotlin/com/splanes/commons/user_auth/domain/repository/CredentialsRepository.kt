package com.splanes.commons.user_auth.domain.repository

import com.splanes.commons.user_auth.domain.model.credentials.CredentialsRequestBO
import com.splanes.commons.user_auth.domain.model.user.UserData

interface CredentialsRepository {
	suspend fun find(user: UserData): CredentialsRequestBO?
	suspend fun add(credentials: CredentialsModel): Boolean
	suspend fun update(old: CredentialsModel, updated: CredentialsModel): Boolean
}