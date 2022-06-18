package com.splanes.commons.user_auth.infrastructure.entity.user

import org.springframework.data.annotation.Id
import java.sql.Date
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("tbl_user_data")
data class UserDataEntity(
	@Id
	val _id: Int,

	val uuid: String,

	val email: String,

	@field:Column("first_name")
	val name: String,

	@field:Column("last_name")
	val lastName: String,

	val alias: String,

	@field:Column("date_creation")
	val dateCreation: Date,

	@field:Column("date_last_login")
	val dateLastLogin: Date
)
