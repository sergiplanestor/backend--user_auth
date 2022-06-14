package com.splanes.commons.user_auth.infrastructure.mapper

import com.splanes.commons.user_auth.domain.model.user.UserData
import com.splanes.commons.user_auth.domain.model.user.simplify
import com.splanes.commons.user_auth.infrastructure.entity.user.UserDataEntity
import java.sql.Date

class UserMapper : Mapper<UserData, UserDataEntity> {

    inline fun <reified D : UserData> userOf(entity: UserDataEntity): D =
        with(domainOf(entity)) {
            when (D::class) {
                UserData.Simple::class -> simplify()
                UserData.Detailed::class -> this
                else -> error("User data type provided not supported.")
            }
        } as D

    override fun domainOf(entity: UserDataEntity): UserData.Detailed = with(entity) {
        UserData.Detailed(
            uuid = uuid,
            email = email,
            alias = alias,
            name = name,
            lastName = lastName,
            dateCreatedOn = dateCreation,
            dateLastLogin = dateLastLogin
        )
    }

    override fun entityOf(bo: UserData) =
        when (bo) {
            is UserData.Detailed -> with(bo) {
                UserDataEntity(
                    uuid = uuid,
                    email = email,
                    name = name,
                    lastName = lastName,
                    alias = alias,
                    dateCreation = Date(dateCreatedOn.time),
                    dateLastLogin = Date(dateLastLogin.time)
                )
            }
            is UserData.Simple -> error("Simple user data model cannot be converted to entity.")
        }
}