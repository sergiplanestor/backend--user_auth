package com.splanes.commons.user_auth.domain.model.user

import java.util.Date

sealed class UserData(
    open val uuid: String,
    open val email: String,
    open val alias: String,
) {
    data class Simple(
        override val uuid: String,
        override val email: String,
        override val alias: String
    ) : UserData(uuid, email, alias)

    data class Detailed(
        override val uuid: String,
        override val email: String,
        override val alias: String,
        val name: String,
        val lastName: String,
        val dateCreatedOn: Date,
        val dateLastLogin: Date,
    ) : UserData(uuid, email, alias)

    sealed class Matcher(open val value: String) {
        data class Uuid(override val value: String) : Matcher(value)
        data class Email(override val value: String) : Matcher(value)
        data class Alias(override val value: String) : Matcher(value)
        companion object
    }
}

