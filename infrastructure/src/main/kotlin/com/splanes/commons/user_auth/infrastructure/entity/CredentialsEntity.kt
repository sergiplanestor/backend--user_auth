package com.splanes.commons.user_auth.infrastructure.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("credentials")
data class CredentialsEntity(
    @Id val id: String,
    val email: String,
    val password: String
)
