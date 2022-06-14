package com.splanes.commons.user_auth.infrastructure.repository.db

import com.splanes.commons.user_auth.infrastructure.entity.CredentialsEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CredentialsDatabaseRepository : ReactiveCrudRepository<CredentialsEntity, Long> {
}