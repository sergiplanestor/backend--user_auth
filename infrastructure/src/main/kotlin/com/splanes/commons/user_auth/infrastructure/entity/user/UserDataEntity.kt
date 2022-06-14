package com.splanes.commons.user_auth.infrastructure.entity.user

import org.springframework.data.annotation.Id
import java.sql.Date


data class UserDataEntity(
    @Id val uuid: String,
    val email: String,
    val name: String,
    val lastName: String,
    val alias: String,
    val dateCreation: Date,
    val dateLastLogin: Date
)
