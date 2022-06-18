package com.splanes.commons.user_auth.infrastructure.entity

import java.sql.Date
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("tbl_credentials")
data class CredentialsEntity(
	@Id val _id: Int,
	val uuid: String,
	val pwd: String,
	@field:Column("date_creation") val dateCreation: Date,
	@field:Column("date_expiration") val dateExpiration: Date,
	@field:Column("refreshed_count") val timesRefreshed: Int
)
